#
#  Copyright (c) 2014  Ng Pan Wei
#  
#  Permission is hereby granted, free of charge, to any person 
#  obtaining a copy of this software and associated documentation files 
#  (the "Software"), to deal in the Software without restriction, 
#  including without limitation the rights to use, copy, modify, merge, 
#  publish, distribute, sublicense, and/or sell copies of the Software, 
#  and to permit persons to whom the Software is furnished to do so, 
#  subject to the following conditions: 
#  
#  The above copyright notice and this permission notice shall be 
#  included in all copies or substantial portions of the Software. 
#  
#  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
#  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF 
#  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
#  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS 
#  BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN 
#  ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN 
#  CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE 
#  SOFTWARE. 
# 
# language:zh-CN
# encoding:utf-8

@Chinese
功能:用户管理
［为了］保护系统免受未经授权的访问
［作为］管理员
［我要］创建帐户给授权的用户

场景: 给用户创建帐户
    假如 管理员新增用户 "abc" 邮箱 "abc@abc.com"
    假如 用户 "abc" 把密码设为 "abc-password"
    当   用户 "abc" 登入
    那么 系统给用户建立一个会期