Cleanup
figure(1);
hold all
xlabel('t')
ylabel('y')
figure(2);
hold all
xlabel('y')
ylabel('dy')
y0 = 2; % BLUE
y1 = 10; % GREEN
bee = [0 0 1];
%y2;
global n
for n = [0 0.03 7];
    %n = 7;
    bee=circshift(bee,[1 1]) ;
    [t,Y]=ode45(@dYdtfun,[0 50],[y0 y1]); %numerical method
    figure(1)
    plot(t,Y(:,1),'Color', bee)
    figure(2)
    plot(Y(1,1),Y(1,2),'x','Color', bee)
    plot(Y(:,1),Y(:,2),'Color', bee)
    
end

figure(1)
legend ('n = 0', 'n = 0.03', 'n = 7')
figure(2)
legend ('','n = 0','' ,'n = 0.03','' ,'n = 7')

hold off