FROM python:2.7

ADD dab-app.tgz ./
RUN tar -xvf dab-app.tgz
WORKDIR /dab-app

RUN python import_dependencies.py

ENTRYPOINT [ "python" ]

CMD [ "application.py" ]