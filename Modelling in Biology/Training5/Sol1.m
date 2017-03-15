Cleanup
hold
y0 = 2; % BLUE
y1 = 10; % GREEN
%y2;
global n
for n = [0 0.03 7];
%n = 7;
    [t,Y]=ode45(@dYdtfun,[0 10],[y0 y1]); %numerical method
    figure(1)
    plot(t,Y(:,1))    
end


%subplot(2,1,1)
%plot(t,M(:,1),'b')
%subplot(2,1,2)
%plot(t,M(:,2),'g')

hold off