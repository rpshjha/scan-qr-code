@echo off

SET file_name=C:\Users\luckumari\Toren1BD.posters
(
echo poster wall
echo size 2 2
echo position -0.807 0.320 5.316
echo rotation 0 -150 0
echo default poster.png
echo.
echo poster table
echo size 1 1
echo position -2.205 -0.077 3.949
echo rotation -90 0 120
echo.
echo poster custom
echo size 1 1
echo position 0 0 -1.8
echo rotation 0 0 0
echo default custom.png
) > %file_name%

exit