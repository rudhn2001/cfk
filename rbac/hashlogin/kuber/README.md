
# CFK Deployment

Run the producer and consumer application on kubernetes along with 3 zookeeper and 3 kafka servers.

## Steps to Launch Confluent Platform



Step 1 : Create a namespace and set it to default

```bash
sudo kubectl create namespace confluent
```
```bash
sudo kubectl config set-context --namespace confluent
```

Step 2 : Install Confluent for Kubernetes (CFK)

```bash
sudo helm repo add confluentinc https://packages.confluent.io/helm
```
```bash
sudo helm repo update
```

```bash
sudo helm upgrade --install confluent-operator confluentinc confluent-for-kubernetes
```
Step 3 : Install Confluent Platform which you created in the confluent-secure.yaml

```bash
sudo kubectl apply -f confluent-secure.yaml
```
- This should create 3 zookeeper pods, 3 Kafka pods and 1 control center pod
- Wait until all the pods are deployed and running. To check use :

```bash
sudo kubectl get pods
```
- add -w to monitor

Step 4 : View it in Control center (optional)

```bash
sudo kubectl port-forward controlcenter-0 9021:9021
```
- this should forward the kafka server port so that the Control center can access the broker and list the clusters and topics. This is a DashBoard


## Steps to Launch Producer and Consumer


Step 1 : Go to the Producer directory where the produce-deploy.yaml file exists and deploy the resource

- Before running configure the code accordingly if you make changes

```bash
sudo kubectl apply -f produce-deploy.yaml
```
Step 2 : wait for the pod to run

```bash
sudo kubectl get pods
```
Step 3 : Once the pods are running, use the pod logs to check whether the messages are produced

```bash
sudo kubectl logs <producer_pod_name>
```
- add -f to monitor the logs and --timestamp=true for checking timestamps for each log

Step 4 : View it in Control center (optional)

- Since the control center is running you can directly go to the URL and see the topics and messages

```bash
http://localhost:9021
```

Step 5 : Repeat the steps for Consumer as well

```bash
sudo kubectl apply -f consumer.yaml
```

```bash
sudo kubectl logs <consumer_pod_name>
```