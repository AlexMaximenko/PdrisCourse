#!/usr/bin/env python3

import urllib.request
import time


while True:
    time.sleep(10)
    fp = urllib.request.urlopen("http://server-service:3001")

    encodedContent = fp.read()
    decodedContent = encodedContent.decode("utf8")
    print(decodedContent)

    fp.close()
