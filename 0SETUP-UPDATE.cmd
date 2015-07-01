@echo off

gradlew setupDecompWorkspace --refresh-dependencies --gradle-user-home "x:\gradlehome"

pause

gradlew ideaModule --gradle-user-home "x:\gradlehome"



