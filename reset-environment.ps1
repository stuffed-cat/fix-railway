# 环境设置脚本
Write-Host "开始设置 Minecraft Forge 开发环境..." -ForegroundColor Green

# 设置 Java 环境变量
$env:JAVA_HOME = [System.Environment]::GetEnvironmentVariable("JAVA_HOME", "Machine")
Write-Host "使用 JAVA_HOME: $env:JAVA_HOME" -ForegroundColor Cyan

# 清理 Gradle 缓存
$gradleUserHome = "$env:USERPROFILE\.gradle"
Write-Host "清理 Gradle 缓存..." -ForegroundColor Yellow

# 清理 ForgeGradle 缓存
if (Test-Path "$gradleUserHome\caches\forge_gradle") {
    Remove-Item -Path "$gradleUserHome\caches\forge_gradle\*" -Recurse -Force -ErrorAction SilentlyContinue
    Write-Host "ForgeGradle 缓存已清理" -ForegroundColor Green
}

# 清理 Minecraft 缓存
if (Test-Path "$gradleUserHome\caches\minecraft") {
    Remove-Item -Path "$gradleUserHome\caches\minecraft\*" -Recurse -Force -ErrorAction SilentlyContinue
    Write-Host "Minecraft 缓存已清理" -ForegroundColor Green
}

# 运行 Gradle 清理
Write-Host "运行 Gradle 清理..." -ForegroundColor Yellow
& .\gradlew.bat --stop
& .\gradlew.bat clean --refresh-dependencies --info

# 生成 Gradle 运行时环境
Write-Host "生成 Gradle 运行时环境..." -ForegroundColor Yellow
& .\gradlew.bat --stop
& .\gradlew.bat genEclipseRuns --refresh-dependencies --stacktrace

Write-Host "环境设置完成！" -ForegroundColor Green
Write-Host "现在您可以尝试使用以下命令运行构建：" -ForegroundColor Cyan
Write-Host ".\gradlew.bat build" -ForegroundColor White
