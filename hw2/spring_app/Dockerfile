FROM alpine:3.20

RUN apk add openssh-server openrc python3

RUN openrc && touch /run/openrc/softlevel

RUN  ssh-keygen -f /etc/ssh/ssh_host_rsa_key -N '' -t rsa

RUN echo 'PermitRootLogin yes' >> /etc/ssh/sshd_config

RUN echo "root:ansible" | chpasswd

CMD ["/usr/sbin/sshd", "-D"]
