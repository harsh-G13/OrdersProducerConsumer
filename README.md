# 🚀 Java Order Processing System ⚡
### A High-Performance Multi-threaded Order Processor with Spring Boot


## 🌟 Features

- **Multi-threaded Processing** 🧵
- **Real-time Metrics Dashboard** 📊
- **Spring Boot Powered** 🍃
- **Configurable Thread Pools** ⚙️
- **Graceful Shutdown** 🛑

```java
// Sample Order Creation
@Scheduled(fixedRate = 1, timeUnit = TimeUnit.SECONDS)
public void generateOrder() {
    Order order = new Order(UUID.randomUUID());
    orderQueue.put(order); // Thread-safe insertion
}
```
<img width="878" height="446" alt="image" src="https://github.com/user-attachments/assets/3683717a-fceb-4036-bb5b-f1dcd53f0f94" />

🛠️ Tech Stack

- **Core Framework**	- Spring Boot 3.x
- **Thread Managemen**t	- Java ExecutorService
- **Queue**	- BlockingQueue
- **Concurrency**	- Atomic Variables
- **Monitoring**	- Custom Metrics
