Сам код pipeline находится в Jenkinsfile

## Пруфы работоспособности:
![image](https://github.com/user-attachments/assets/0704e3d2-342a-4239-8ca2-54946549088f)

Sonar ругается на уровень дубликации строк, так как проект игрушечный:

![image](https://github.com/user-attachments/assets/d6caae12-752f-45b7-9677-c2272e78f27f)

![image](https://github.com/user-attachments/assets/f14bfc10-d44c-4e36-a294-e393110d15ed)

![image](https://github.com/user-attachments/assets/070fff37-31d9-4f7d-84bf-dd197b541e9b)

Успешная отправка вебхуков:

![image](https://github.com/user-attachments/assets/debaab29-93c7-4d36-a4da-4a8401c2e27b)


Настройки подключения к sonar:

![image](https://github.com/user-attachments/assets/10ae1a9b-f511-467b-8977-9dc85d9094a1)

Настройки джобы:
![image](https://github.com/user-attachments/assets/17fb0236-2ee9-40bf-8a75-53fac9a9d6aa)


## Лог выполнения:

Скопипастила из чатика увеличение размера кучи для запуска sonar:
```sysctl -w vm.max_map_count=524288
sysctl -w fs.file-max=131072
ulimit -n 131072
ulimit -u 8192
```

Jenkins и sonar запустила через jenkins-docker-compose.yml

Через tuna(аналог ngrok) пробросила адрес дженкинса

Добавила плагин и установила через tools maven, allure

В sonar сгенерировала токин для jenkins и добавила webhook для отправки отчета

Добавила плагин и прописала в системных настройках адрес sonar

Написала jenkinsfile
