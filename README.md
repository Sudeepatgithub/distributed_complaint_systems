# distributed_complaint_systems
Distributed Systems Project - Microservices, AWS, Docker and Queuing

curl -X POST \
 http://localhost:8080/publish/122 \
 -H 'Content-Type: application/json' \
 -H 'Postman-Token: 1adbde51-cf35-40b4-ba18-9740202c8002' \
 -H 'cache-control: no-cache' \
 -d '{
	"id" : "sqs-122",
	"userId" : "18200064",
	"description" : "Hello I want to get my lights fixed.",
	"departmentId" : "maintenance",
	"timestamp"  : "1545931594"
}’
