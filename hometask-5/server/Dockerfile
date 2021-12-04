FROM python:latest

EXPOSE 3001

COPY server.py /server/
COPY index.html /server/

WORKDIR /server/

CMD ["python", "./server.py"]
