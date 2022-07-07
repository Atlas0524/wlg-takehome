FROM gradle:7.4.2-jdk18

ADD . /wlg-takehome/
WORKDIR /wlg-takehome/

CMD ["sleep", "inf"]