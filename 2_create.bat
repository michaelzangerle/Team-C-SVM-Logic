@echo off
set IMQ_HOME="C:\glassfishv3\mq"

%IMQ_HOME%\bin\imqobjmgr add -t tf -l "svm:topicFactory"  -j java.naming.provider.url=file:///C:/temp -j java.naming.factory.initial=com.sun.jndi.fscontext.RefFSContextFactory -f

%IMQ_HOME%\bin\imqobjmgr add -t t -l "svm/subTeam" -o "imqDestinationName=svmsubTeam" -j java.naming.provider.url=file:///C:/temp -j java.naming.factory.initial=com.sun.jndi.fscontext.RefFSContextFactory -f
%IMQ_HOME%\bin\imqobjmgr add -t t -l "svm/member" -o "imqDestinationName=svmmember" -j java.naming.provider.url=file:///C:/temp -j java.naming.factory.initial=com.sun.jndi.fscontext.RefFSContextFactory -f

pause