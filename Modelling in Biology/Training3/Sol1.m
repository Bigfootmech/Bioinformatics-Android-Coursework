Cleanup
x0 = 2; % BLUE
y0 = -1; % GREEN
    global a b g
    a = 1;
    b = 1;
    g = 0.3;

% V = Vector, M = Matrix
[t,M]=ode45(@dVdtfun,[0 10],[x0 y0]); %numerical method

%subplot(2,1,1)
plot(t,M(:,1),'b')
hold
%subplot(2,1,2)
plot(t,M(:,2),'g')