# SysML v2 API and Services using Docker

## Install Docker for Ubuntu
1. Follow the steps from the [Docker website](https://docs.docker.com/engine/install/ubuntu/)
1. (Optional) [Add user to docker group](https://docs.docker.com/engine/install/linux-postinstall/).
   - Note: If done on a VM, you will have to restart the VM to get these changes to take place.

## Deploy with Docker Compose
A `docker-compose.yml` file has been created to deploy both the SysML server and a postgres database. This is the suggested method if you just need things running. You can choose the initial state of the postgres database by commenting/uncommenting the files under `services:sysmlv2postgres:volumes`. `examples.sql`, `training.sql`, and `validation.sql` have been created from the `.sysml` files on the [SysML v2 GitHub](https://github.com/Systems-Modeling/SysML-v2-Pilot-Implementation/tree/master/sysml/src).
1. Download the artifacts from the [Jenkins build](https://mcjenkins.ansys.com/view/Trunk/job/SysMLv2MockServer/)
1. Extract the artifacts and cd into the directory.
1. Log in to Docker Hub:
    - `docker login`
1. Run with Docker compose:
   - `docker compose -f docker-compose.yml up`
1. Once the database signals that it is ready, you should be able to connect to the Swagger API page to perform calls to the API by visiting `localhost:9000/docs/`. Similarly, you should be able to connect to the server from another machine by using the machine running the docker image's ip. For example, `192.168.10.1:9000/docs/`.
1. (Optional) Clean up docker compose: 
   -`docker compose -f docker-compose.yml down`

## Deploy without Docker Compose
If you'd prefer to run without using the docker compose file:
   1. `docker network create sysmlv2`
   1. `docker run --name sysmlv2postgres --network sysmlv2 -p 5432:5432 -e POSTGRES_PASSWORD=mysecretpassword -e POSTGRES_DB=sysml2 -d postgres:alpine`
   1. `docker run -it --rm --name sysmlv2server -p 9000:9000 --network sysmlv2 phoenixintegration/mccdev:<version>-mock.sysml2.server.<postfix>`
   1. Once the database signals that it is ready, you should be able to connect to the Swagger API page to perform calls to the API by visiting `localhost:9000/docs/`. Similarly, you should be able to connect to the server from another machine by using the machine running the docker image's ip. For example, `192.168.10.1:9000/docs/`.
