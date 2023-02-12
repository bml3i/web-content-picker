# FROM openjdk:8-jdk-alpine
# COPY build/libs/svc-web-content-picker-1.0-SNAPSHOT.jar app.jar
# ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM --platform=linux/amd64 centos:7 AS webDriverJdkReady

# 安装JDK
ADD jdk-8u251-linux-x64.tar.gz /usr/local/

# 安装web browser
RUN yum -y install firefox

# 安装web driver
RUN curl -fL -o /tmp/geckodriver.tar.gz \
    https://github.com/mozilla/geckodriver/releases/download/v0.32.1/geckodriver-v0.32.1-linux-aarch64.tar.gz \
&& tar -xzf /tmp/geckodriver.tar.gz -C /tmp/ \
&& chmod +x /tmp/geckodriver \
&& mv /tmp/geckodriver /usr/local/bin/


# 安装中文字体
RUN yum -y groupinstall "fonts"



ENV JAVA_HOME=/usr/local/jdk1.8.0_251
ENV PATH=$PATH:$JAVA_HOME/bin
ENV CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar


FROM --platform=linux/amd64 webDriverJdkReady AS svcContentPicker

COPY build/libs/svc-web-content-picker-1.0-SNAPSHOT.jar app.jar
# ENTRYPOINT ["java", "-jar", "/app.jar"]

ENTRYPOINT ["tail", "-f", "/dev/null"]