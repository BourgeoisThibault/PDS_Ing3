FROM python:2.7

ADD dab-app ./

RUN python import_dependencies.py

ENTRYPOINT [ "python" ]

CMD [ "application.py" ]
