@echo off
javac AgentFx/func/Array2List.java
javac AgentFx/NetAddress/listaddress.java
javac --module-path=C:\Java\openjfx-11.0.2\lib --add-modules javafx.controls AgentFx/Agent.java
java --module-path=C:\Java\openjfx-11.0.2\lib --add-modules javafx.controls AgentFx.Agent