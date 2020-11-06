# netflix系微服务-书城 
### 技术栈
+ eureka
+ zuul
+ spring-cloud-config
+ spring-cloud-stream
+ RabbitMQ
+ hystrix
+ spring-data-jpa
+ hystrix-dashboard
+ turbine
+ ribbon

### 项目目录
#### 公共包
+ [cloud-api-com](https://github.com/quan930/cloud-netflix/tree/main/cloud-api-com)
#### 基本配置
+ [cloud-config](https://github.com/quan930/cloud-netflix/tree/main/cloud-config)
+ [cloud-eureka-server](https://github.com/quan930/cloud-netflix/tree/main/cloud-eureka-server)
+ [cloud-hystrix-dashboard](https://github.com/quan930/cloud-netflix/tree/main/cloud-hystrix-dashboard)
+ [cloud-turbine-service](https://github.com/quan930/cloud-netflix/tree/main/cloud-turbine-service)
+ [cloud-zuul](https://github.com/quan930/cloud-netflix/tree/main/cloud-zuul)

#### 服务
+ [cloud-person-server](https://github.com/quan930/cloud-netflix/tree/main/cloud-person-server)
+ [cloud-book-server](https://github.com/quan930/cloud-netflix/tree/main/cloud-book-server)
+ [cloud-order-server](https://github.com/quan930/cloud-netflix/tree/main/cloud-order-server)
+ [cloud-shop-server](https://github.com/quan930/cloud-netflix/tree/main/cloud-shop-server)

#### test
+ [cloud-test](https://github.com/quan930/cloud-netflix/tree/main/cloud-test)



### `书城API`
#### 用户服务
+ 查询全部用户
    ```http request
    GET http://localhost/api/v1/person
    Accept: application/json
    ```
+ 查询指定用户
    ```http request
    GET http://localhost/api/v1/person/{id}
    Accept: application/json
    ```
+ 查询指定用户权限
    ```http request
    GET http://localhost/api/v1/person/{id}/permissions
    Accept: application/json
    ```
+ 添加用户
    ```http request
    POST http://localhost/api/v1/person
    Content-Type: application/json
    
    {
      "name": "tom4",
      "password": "111111",
      "permissions": 1
    }
    ```
+ 修改用户
    ```http request
    PUT http://localhost/api/v1/person
    Content-Type: application/json
    
    {
      "id": "4028ab157595e815017595fd73db0000",
      "name": "tom3",
      "password": "111111",
      "permissions": 3
    }
    ```
#### 书籍服务
+ 查询全部书籍
    ```http request
    GET http://localhost/api/v1/book
    Accept: application/json
    ```
+ 查询指定书籍(id)
    ```http request
    GET http://localhost/api/v1/book/{id}
    Accept: application/json
    ```
+ 添加书籍
    ```http request
    POST http://localhost/api/v1/book
    Content-Type: application/json
    
    {
      "name": "Core JAVA",
      "category": "JAVA",
      "repertory": 20
    }
    ```
+ 修改书籍
    ```http request
    PUT http://localhost/api/v1/book
    Content-Type: application/json
    
    {
      "id": "4028ab1575961aa4017596292cc30000",
      "name": "Core JAVA",
      "category": "JAVA",
      "repertory": 18
    }
    ```
+ 修改书籍库存
    ```http request
    POST http://localhost/api/v1/book/repertory
    Content-Type: application/json
    
    {
      "id": "4028ab1575961aa4017596292cc30000",
      "repertory": 3
    }
    ```
#### 订单服务
+ 查询全部订单
    ```http request
    GET http://localhost/api/v1/order
    Accept: application/json
    ```
+ 查询指定订单
    ```http request
    GET http://localhost/api/v1/order/{id}
    Accept: application/json
    ```
+ 添加订单
    ```http request
    POST http://localhost/api/v1/order
    Content-Type: application/json
    
    {
      "goodsId":"4028ab15757d90cf01757d9456bb0000",
      "userId":"4028ab157592067d0175921687ab0000"
    }
    ```
+ 修改订单
    ```http request
    PUT http://localhost/api/v1/order
    Content-Type: application/json
    
    {
      "id": "4028ab157595edc2017596046fd50000",
      "userId": "4028ab157592067d0175921687ab0000",
      "goodsId": "4028ab15757d90cf01757d9456bb0000",
      "date": "2020-11-05T02:30:54.000+00:00"
    }
    ```
#### 商店服务
+ 购买书籍
    ```http request
    POST http://127.0.0.1/api/v1/shop
    Content-Type: application/json
    
    {
      "goodsId":"4028ab1575961aa4017596292cc30000",
      "userId":"4028ab157568873f01756898bd460000"
    }
    ```