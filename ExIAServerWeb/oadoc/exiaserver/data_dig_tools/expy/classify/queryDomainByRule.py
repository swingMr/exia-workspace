#!/usr/bin/env python
# encoding: utf-8

import re
import sys

def getDomainByRule(str,rule):
    mo1 = re.match(rule,str)
    # mo1=phoneNumberRegex.search(str)
    # print(mo1)
    if mo1:
        return "True"
    else:
        return "False"
if __name__ == '__main__':
    print (getDomainByRule(sys.argv[1],sys.argv[2]))