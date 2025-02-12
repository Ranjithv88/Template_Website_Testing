@echo off
:: Set the default message color to white using PowerShell
powershell -Command "Write-Host 'TEMPLATE WEBSITE BACKEND' -ForegroundColor White"
pause
echo.

:: Check if the JAR file exists
if not exist "Template-0.0.1-SNAPSHOT.jar" (
    :: Error message in red using PowerShell
    powershell -Command "Write-Host 'JAR file \"Template-0.0.1-SNAPSHOT.jar\" does not exist.' -ForegroundColor Red"
    echo.
    powershell -Command "Write-Host 'Cloning the repository...' -ForegroundColor White"
    echo.
    git clone https://github.com/Ranjithv88/Template_Build.git
    echo.
    if %ERRORLEVEL% NEQ 0 (
        :: Error message in red using PowerShell
        powershell -Command "Write-Host 'There was an issue cloning the repository. Please check for errors.' -ForegroundColor Red"
        pause
        exit /b
    )
    :: Success message in green using PowerShell
    powershell -Command "Write-Host 'Repository cloned successfully.' -ForegroundColor Green"
    echo.
    cd Template_Build
) else (
    :: Success message in green using PowerShell
    powershell -Command "Write-Host 'Template-0.0.1-SNAPSHOT.jar File Check Ok ...!' -ForegroundColor Green"
    echo.
)

:: Check if Java is installed
powershell -Command "Write-Host 'Checking if Java is installed...' -ForegroundColor White"
echo.
java -version >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    :: Error message in red using PowerShell
    powershell -Command "Write-Host 'Java is not installed or not found in the PATH.' -ForegroundColor Red"
    powershell -Command "Write-Host 'Please install Java from https://www.java.com/download/ and ensure it is added to the system PATH.' -ForegroundColor Red"
    echo.
    pause
    exit /b
)

:: Success message in green using PowerShell
powershell -Command "Write-Host 'Java is installed. Version:' -ForegroundColor Green"
powershell -Command "java -version | ForEach-Object {Write-Host $_ -ForegroundColor Green}"
echo.

:: Check if MySQL is installed and running
powershell -Command "Write-Host 'Checking the MySQL Server ' -ForegroundColor White"
echo.
mysql --version >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    :: Error message in red using PowerShell
    powershell -Command "Write-Host 'MySQL is not installed or not found in the PATH.' -ForegroundColor Red"
    powershell -Command "Write-Host 'Please install MySQL from https://dev.mysql.com/downloads/ and ensure it is added to the system PATH.' -ForegroundColor Red"
    echo.
    pause
)
powershell -Command "Write-Host 'MySQL Server is Running ....! ' -ForegroundColor Green"

:: Message in white using PowerShell
powershell -Command "Write-Host 'You change the password into ''root'' and create the schema or database name as Template.' -ForegroundColor Yellow"
echo.

:: Run the JAR file
powershell -Command "Write-Host 'Running the JAR file...' -ForegroundColor White"
echo.
java -jar Template-0.0.1-SNAPSHOT.jar
if %ERRORLEVEL% NEQ 0 (
    :: Error message in red using PowerShell
    powershell -Command "Write-Host 'There was an issue running the JAR file. Please check for errors.' -ForegroundColor Red"
    echo.
    pause
    exit /b
)

:: Success message in green using PowerShell
powershell -Command "Write-Host 'Script completed successfully.' -ForegroundColor Green"

