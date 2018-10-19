
## Concourse deployment

### Login to the concourse server

```
fly -t blockchain login -c https://concourse.blockchain.cf-demo.com
```

### Deploy the pipeline

```
fly -t blockchain set-pipeline -p spring-boot-sample-blockchain -c ci/spring-boot-sample-blockchain.yml -l ../../concourse/concourse.yml
```