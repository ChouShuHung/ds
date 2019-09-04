# DS - password validation service(Docker configuration)
Write a password validation service, meant to be configurable via IoC (using dependency injection engine of your choice). The service is meant to check a text string for compliance to any number of password validation rules. The rules currently known are:
 
⦁Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each.
⦁Must be between 5 and 12 characters in length.
⦁Must not contain any sequence of characters immediately followed by the same sequence.

## Overview

Following are a few aspects being demonstrated:

- Build the project and create a Docker image
- Docker Swarm - Deployment using Docker Compose with 5 replicas and visualizer

## Prerequisites
- JDK 8
- Docker
- Gradle

## Dev Environment
This application was tested on the following:

- OS : OS X Mojave 10.14.6 x86_64
- Docker: 19.03.1 (community)
- Gradle: 5.5.1

## Instructions

### Build the project and create a Docker image

- Create a docker image during a gradle project build
	 - $ gradle build docker

- Run the docker image
	 - $ docker run -p 8080:8888 name/image:version 

- Verify that Restful API endpoint is accessible
	 - $ curl http://localhost:8080/validatePassword
	 
### Docker Swarm
- Create VMs using docker-machine
     - $ docker-machine create --driver virtualbox myvm1
     - $ docker-machine create --driver virtualbox myvm2
    
- Instruct myvn1 to be a swarm manager 
     - $ docker-machine ssh myvm1 "docker swarm init --advertise-addr <myvm1 ip>"

- Send the join command to myvm2 to join the swarm
     - $ docker-machine ssh myvm2 "docker swarm join --token token ip:2377"
          
- Configure VM myvm1
     - $ eval $(docker-machine env myvm1)
     
- Deploy using Docker compose file
	 - $ docker stack deploy -c docker-compose.yml ds

- Unsetting docker-machine shell variable settings
     - $ eval $(docker-machine env -u)

- Check the visualize at:
	 - http://127.0.0.1:5000/
	 
- Verify that Restful API endpoint is accessible
     - $ curl http://localhost:4000/validatePassword

## Reference

- https://docs.docker.com/
- https://docs.docker.com/engine/docker-overview/
- https://github.com/palantir/gradle-docker
- https://github.com/bijeshos/spring-boot-with-docker
- https://github.com/microscaling/force12-lb-example
- https://medium.com/tech-tajawal/create-cluster-using-docker-swarm-94d7b2a10c43