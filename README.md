# AndroidStackBlur
[ ![Download](https://api.bintray.com/packages/cangol/maven/AndroidStackBlur/images/download.svg) ](https://bintray.com/cangol/maven/AndroidStackBlur/_latestVersion)
[![Build Status](https://travis-ci.org/Cangol/AndroidStackBlur.svg?branch=master)](https://travis-ci.org/Cangol/AndroidStackBlur)

## 说明
本项目主要方便大家在android上使用高斯模糊。  
提供了三种方法实现高斯模糊：Java实现、RenderScript实现、C实现
使用者可根据实际情况选择使用

## 引用方式
Maven

     <dependency>
         <groupId>mobi.cangol.mobile</groupId>
         <artifactId>stackblur</artifactId>
         <version>1.0.0</version>
         <type>pom</type>
     </dependency>
Gradle
 
    compile 'mobi.cangol.mobile:stackblur:1.0.0'
## 使用方法：
    
    StackBlurManager stackBlurManager=new StackBlurManager(BitmapFactory.decodeResource(getResources(),R.mipmap.test));
    stackBlurManager.process(70);
    stackBlurManager.processRenderScript(MainActivity.this,70);
    stackBlurManager.processNatively(70);
    

   
