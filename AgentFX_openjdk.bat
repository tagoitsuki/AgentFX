@echo off
javac AgentFx/func/*.java
javac AgentFx/NetAddress/*.java
javac --module-path=C:\Java\openjfx-11.0.2\lib --add-modules javafx.controls AgentFx/network/*.java
javac --module-path=C:\Java\openjfx-11.0.2\lib --add-modules javafx.controls AgentFx/*.java
java --module-path=C:\Java\openjfx-11.0.2\lib --add-modules javafx.controls AgentFx.Agent