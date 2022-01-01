# -*- coding: utf-8 -*-

from __future__ import absolute_import

def validate_args(self,params,required,defaults):
    for item in required:
        if item not in params:
            raise ValueError('Required argument '+item+' is missing!')
    for key in defaults:
        if key not in params:
            params[key] = defaults[key]
    return params