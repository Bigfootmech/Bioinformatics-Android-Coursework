Cleanup

figure(1);
hold all
xlabel('t')        %Label Axes Diagram 1 (time plane)
ylabel('y')
figure(2);
hold all
xlabel('y')        %Label Axes Diagram 2 (phase plane)
ylabel('dy')

a = 1;  %redundant count variable
y0 = 2; % initial condition y =
y1 = 10; % initial condition dy/dt =
bee = [0 0 1; 0 1 1]; %set up 6 different combinations of colours to use.
global n %injet n in to ODE


for n = [0 0.3 2 7];
    bee=circshift(bee,[0 1]) ; %use different colours
    if (a == 4) 
        bee=circshift(bee,[1 0]); %after 3, use new colours
    end
    
    string = ['n = ' num2str(n)];   %make a string to label current n value in the legend
    zee(a) = {string};              %save strings in correct order
    a = a + 1;
    
    [t,Y]=ode45(@dYdtfun,[0 50],[y0 y1]); %numerical method, long time
    figure(1)
    plot(t,Y(:,1),'Color', bee(1,:))            %Plot time plane
    figure(2)
    plot(Y(:,1),Y(:,2),'Color', bee(1,:))       %Plot phase plane
end

plot(Y(1,1),Y(1,2),'x','Color', [0 0 0])   %plot starting point

figure(1)
legend (zee)
figure(2)
legend (zee, 'starting point')

hold off