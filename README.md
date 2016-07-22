<p align="center">
 <img src ="/banniere.png", align="center"/>
</p>
# SimpleDroidRx
An application that helps you learn and better understand ReactiveX on Android.

### Introduction
I've created this project because, as anybody knows, learn ReactiveX from scratch it's an awesome challenge, and it has a pretty steep learning curve. So I try to bring together all samples I found useful to better understand ReactiveX and how to use it on an Android app.
### How it works ?
With SimpleDroidRx app, you have several fragments. Each one contains some examples of ReactiveX. Each example is performed using those Rx libraries :
* [RxJava] : RxJava is a Java VM implementation of Reactive Extensions: a library for composing asynchronous and event-based programs by using observable sequences.
* [RxAndroid] : Android specific bindings for RxJava.
* [RxBroadcast] : Reactive Broadcast and LocalBroadcast for Android.
* [RxBinding] : RxJava binding APIs for Android UI widgets from the platform and support libraries.

It also uses those awesome libraries:
* [ButterKnife] : Bind Android views and callbacks to fields and methods.
* [Retrofit] :A type-safe HTTP client for Android and Java

### Mini Screenshots
<p align="left">
 <img width=100 src ="/Screenshots/hello_worlds_screenshot.png", align="center"/>
 <img width=100 src ="/Screenshots/famous_operators_screenshot.png", align="center"/>
 <img width=100 src ="/Screenshots/error_handling_screenshot.png", align="center"/>
 <img width=100 src ="/Screenshots/background_tasks_screenshot.png", align="center"/>
 <img width=100 src ="/Screenshots/android_simple_sample_screenshot.png", align="center"/>
 <img width=100 src ="/Screenshots/android_rest_sample_screenshot.png", align="center"/>
</p>

### Demo
You can test this app with appetize: https://appetize.io/app/g57uqpd3m4nn8w45v0r8awfq9w

### List of Samples
Each sample is on a fragment and set up in the navigation drawer. It's sorted by level of complexity (from top to bottom). Also, on each sample, an explanatory TextView is here to briefly explain what's happening:
* [Hello World] : In this fragment, you'll learn how to use an Observable and Observer. Also, you'll create your first stream and use your first operator : map( ).

>EXPLICATION: When you click on button "Map( ) my words", you'll subscribe to an observable on TextView Text. The relative stream (myStream) replaces the words "Massive View Controller" by "MVVM", "Hell Callback" by "ReactiveX" and "AsyncTask" by "RxJava". Finally it adds at the end of TextView "<3 <3 <3".

* [Famous Operator] : In this fragment, you'll learn the most commons operators and get used with them : flatMap( ), filter( ), take( ), doOnNext( )

>EXPLICATION: When you click on "Play Happy !" or use the SeekBar, you'll subscribe to an observable on a string array. The relative stream (myStream) observes each item of string array, and apply to it some functions (setSmileyToItem, setCarriotReturnToItem & filterVersionAndroidThatSucks). It also takes only the number of item set by take( ).Finally, each item is shown in the TextView.

* [Error Handling] : In this fragment, you'll learn how to handle error properly. You'll use map( ) and onError ().

>EXPLICATION: When you press button, you'll subscribe to an observable to relative TextView text. The relative stream (myStreamThatHandleError) executes a function that will test String. If it contains "callback" AND "hell" then it will throw an error.

* [Background Tasks] : In this fragment, you'll learn how to run one and multiple tasks in the background.

>EXPLICATION: When you press the simple "SIMPLE TASK" button, you'll subscribe to an observable that execute a single task running in background (During 4sec) and stop.
When you press the "DOUBLE TASKS" button, you'll subscribe to another one observable that execute a first long task to background (During 8sec) and when finished, a second single task will start (During 4 sec).

* [Android Simple Sample] : In this fragment, you'll learn how to set an observer to a button using RxView.clicks( ), and intercept network changes.

>EXPLICATION: When you start fragment, you subscribe to an observable on network changes. When you disable Wifi or mobile connection, you'll get notified by a Snackbar.
When you press the "SHOW" button, it subscribes to an observable. It will show a Snackbar.

* [Android REST Sample] : In this fragment, you'll learn how to make multiple http requests with only single stream.

>EXPLICATION: When you press "REFRESH" button, it will subscribe to an observable that gets Github followers of each person you've defined (In a String array), and after processing of requests, update the TextView.

### Contribute
It will be awesome if you contribute to this project adding you own sample(s). Just follow this checklist :

- 1 Create a Fragment and name it with the name of your sample. Put it on "Fragments" package. Also, create it's layout. You could use the other fragment as a model.
<p align="left">
 <img src ="/Steps/step1.png", align="center"/>
</p>
- 2 Add your fragment in MainActivity on "displayView" function.
<p align="left">
 <img src ="/Steps/step2.png", align="center"/>
</p>
- 3 Add the title of your sample in string.xml on related array (nav_drawer_labels)
<p align="left">
 <img src ="/Steps/step3.png", align="center"/>
</p>
- 4 You can also modify the icon menu of your sample in NavigationDrawerAdapter on "getRessource" function.
<p align="left">
 <img src ="/Steps/step4.png", align="center"/>
</p>
- 5 Update this README.md with your own infos.
- 6 Make a pull request !

License
-------

    Copyright (C) 2016 Philippe Boisney

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

 [RxJava]: <https://github.com/ReactiveX/RxJava>
 [RxAndroid]: <https://github.com/ReactiveX/RxAndroid>
 [ButterKnife]: <http://jakewharton.github.io/butterknife/>
 [Retrofit]: <http://square.github.io/retrofit/>
 [RxBroadcast]: <https://github.com/cantrowitz/RxBroadcast>
 [RxBinding]: <https://github.com/JakeWharton/RxBinding>
 
 [Hello World]: <https://github.com/PhilippeBoisney/SimpleDroidRx/blob/master/app/src/main/java/com/cookminute/simpledroidrx/Fragments/Hello_World_Fragment.java>
 [Famous Operator]: <https://github.com/PhilippeBoisney/SimpleDroidRx/blob/master/app/src/main/java/com/cookminute/simpledroidrx/Fragments/Famous_Operators_Fragment.java>
 [Error Handling]: <https://github.com/PhilippeBoisney/SimpleDroidRx/blob/master/app/src/main/java/com/cookminute/simpledroidrx/Fragments/Error_Handling_Fragment.java>
 [Background Tasks]: <https://github.com/PhilippeBoisney/SimpleDroidRx/blob/master/app/src/main/java/com/cookminute/simpledroidrx/Fragments/Background_Tasks_Fragment.java>
 [Android Simple Sample]: <https://github.com/PhilippeBoisney/SimpleDroidRx/blob/master/app/src/main/java/com/cookminute/simpledroidrx/Fragments/Android_Simple_Sample_Fragment.java>
 [Android REST Sample]: <https://github.com/PhilippeBoisney/SimpleDroidRx/blob/master/app/src/main/java/com/cookminute/simpledroidrx/Fragments/Android_REST_Sample_Fragment.java>
