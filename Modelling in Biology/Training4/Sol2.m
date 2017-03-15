close all
x0 = 0;                     %initial condition
k = 3/16;                   %initial condition
h=0.01;
t=0:h:1000;
x=zeros(length(t),1);
y=zeros(length(t),1);
x(1) = x0;
o1 = 0.1;
o2 = 5;
%hold; 

%   EULER
    for i = 1:1:length(t)-1
        x(i+1)=x(i)+h*(-k*x(i))+o1*sqrt(h)*randn;
    end
    for i = 1:1:length(t)-1
        y(i+1)=y(i)+h*(-k*y(i))+o2*sqrt(h)*randn;
    end
    figure(1);
    hist(x,t);
    figure(2);
    hist(y,t)
    %th = t(1):t(end)/(length(y)-1):t(end);
mean_x=mean(x)
std_x=std(x)
mean_y=mean(y)
std_y=std(y)
    %plot(th,y)



%hold off