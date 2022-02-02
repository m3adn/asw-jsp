clear; close all;

imgDetect1 = imread("detect_1.jpg");
imgDetect2 = imread("detect_2.jpg");

figure(1);
imshow(imgDetect1);
title('Detect 1');

pause(0.1);
figure(2);
imshow(imgDetect2);
title('Detect 2');

diff = imabsdiff(imgDetect1, imgDetect2);

imgBin = diff>20;

pause(0.1);
figure(3);
imshow(imgBin);
title('Diferenças em binário');