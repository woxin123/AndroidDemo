# SharedPreferences

commit 和 apply 的区别

+ commit()
    - 同步写入
    - 有返回值
    - 同时调用时，最后一次获胜
    
+ apply()
    - 同步写入内存，异步保存磁盘
    - 无返回值
    - 同时调用时，最后一次调用覆盖
    
    