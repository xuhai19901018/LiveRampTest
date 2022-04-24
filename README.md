# LiveRampTest
### 题目如下

	请实现如下要求的轻量级任务编排服务：
	1. 可以按顺序添加任意任务节点形成一个任务链。
	2. 任务需要按顺序执行，但是同一个节点可能有多个任务。
	3. 同一个节点的多个任务，需要可以并行。
	4. 节点完成标识可以指定为都完成或满足指定个数要求，如：至少有一个任务执行完成。
	5. 每个节点任务都可能失败，失败后可以通过接口进行重试。
	6. 实现监控功能，可以监控当前任务状态，如：任务执行到的步骤，任务产生错误的信息
	等。
	
![TS](docs/images/BE.png)
	
	要求：
	1. 请基于Github（https://github.com/）或Gitee（https://gitee.com/）进行代码开发和
	管理，保留开发过程中的commit记录。交付完整可运行的代码，提交到一个git平台的
	public仓库，并回复我们链接。
	2. 需要实现核心逻辑。
	3. 考虑data model和API定义。
	4. 考虑代码的模块化和可复用性。
	5. 尽可能多的实现细节。
	
### 开放接口
-	/task/startTaskChain 开始一个任务链
-	/task/retryTaskChain 重试当前任务链
-	/task/getCurrentProgress 监控任务链当前运行状态

>具体调用方式请参照postman示例：LiveRampTest.postman_collection.json