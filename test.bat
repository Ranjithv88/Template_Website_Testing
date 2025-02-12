@REM @REM @echo off

@REM @REM :: Default color for the script
@REM @REM color 07

@REM @REM :: Normal message in default color (white)
@REM @REM echo This is a normal message in white.

@REM @REM :: Change to green color for this echo statement
@REM @REM color 0A
@REM @REM echo This is a success message in green.

@REM @REM :: Change to red color for this echo statement
@REM @REM color 0C
@REM @REM echo This is an error message in red.

@REM @REM :: Back to default color (white)
@REM @REM color 07
@REM @REM echo This is another normal message in white.
@REM @REM pause


@REM @echo off

@REM :: Enable ANSI escape sequences if needed
@REM echo.

@REM @REM :: Normal message in default color (white)
@REM @REM echo [0;37mThis is a normal message in white.

@REM @REM :: Success message in green (changes color for just this line)
@REM @REM echo [0;32mThis is a success message in green.

@REM @REM :: Error message in red (changes color for just this line)
@REM @REM echo [0;31mThis is an error message in red.

@REM @REM :: Reset back to default color (white)
@REM @REM echo [0mThis is a message in default color after reset.

@REM @REM :: Pause to view the output
@REM @REM pause


@REM @REM @echo off

@REM @REM :: Call PowerShell script for individual line coloring
@REM @REM powershell -Command "Write-Host 'This is a success message in green' -ForegroundColor Green"
@REM @REM powershell -Command "Write-Host 'This is an error message in red' -ForegroundColor Red"
@REM @REM powershell -Command "Write-Host 'This is a message in default white' -ForegroundColor White"

@REM @REM pause

@REM @echo off
@REM :: Enable ANSI escape sequences if necessary (on newer versions of Windows 10)
@REM :: You only need to enable ANSI once, it can be skipped if already done.
@REM echo.

@REM :: Normal message in default color (white)
@REM echo [0;37mThis is a normal message in white.

@REM :: Success message in green (changes color for just this line)
@REM echo [0;32mThis is a success message in green.

@REM :: Error message in red (changes color for just this line)
@REM echo [0;31mThis is an error message in red.

@REM :: Reset back to default color (white)
@REM echo [0mThis is a message in default color after reset.

@REM :: Pause to view the output
@REM pause

@echo off
@echo off

set /p answer=Do you want to continue? (yes/no): 
if /I "%answer%"=="yes" (
    echo You chose yes.
) else if /I "%answer%"=="no" (
    echo You chose no.
) else (
    echo Invalid input. Please enter yes or no.
)
pause


