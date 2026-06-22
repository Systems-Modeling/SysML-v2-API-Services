# ---- Build stage ----
FROM eclipse-temurin:11-jdk-jammy AS builder

RUN apt-get update && \
    apt-get install -y --no-install-recommends curl && \
    rm -rf /var/lib/apt/lists/*

# Install sbt 1.2.8 (matches project/build.properties)
RUN curl -fsSL "https://github.com/sbt/sbt/releases/download/v1.2.8/sbt-1.2.8.tgz" \
      | tar xz -C /usr/local && \
    ln -s /usr/local/sbt/bin/sbt /usr/local/bin/sbt

WORKDIR /app

# Copy build descriptors first so dependency downloads are cached separately
# from source changes.
COPY project/ project/
COPY build.sbt .
RUN sbt update

# Copy everything else and produce the staged distribution
COPY . .
RUN sbt stage

# ---- Runtime stage ----
FROM eclipse-temurin:11-jre-jammy

WORKDIR /app

# Copy the staged Play distribution (bin/, lib/, conf/)
COPY --from=builder /app/target/universal/stage/ .

# Rewrite the hardcoded localhost in persistence.xml to the Docker service name.
# Hibernate reads JDBC properties directly from this file and ignores Play's
# db.default.* config when using its built-in connection pool.
RUN sed -i 's|localhost:5432|postgres:5432|g' conf/META-INF/persistence.xml

COPY docker-entrypoint.sh .
RUN chmod +x docker-entrypoint.sh

EXPOSE 9000

ENTRYPOINT ["./docker-entrypoint.sh"]
