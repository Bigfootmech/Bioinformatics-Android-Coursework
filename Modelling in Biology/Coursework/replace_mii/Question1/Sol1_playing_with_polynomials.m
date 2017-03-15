Cleanup
figure(1)
hold all
figure(2)
hold all

global r k p

p = 1;          %predators 0 = off, 1 = on
r=0.6;
k=4;
%x0=6;
for x0=[0:0.01:3 3:0.25:15 15:1:100]; %x0=[0:0.01:1 1:0.1:3 3:0.25:15 15:1:100];  %0.773578065514523
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ODE45 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    [t,x]=ode45(@dxdtfun,[0 20],[x0]); %numerical method
    figure(1)
    plot(t, x)
    a = 2:1:length(x);
    grar (a) = x(a)-x(a-1);
    grar(1) = grar(2);
    figure(2)
    plot(x,grar)
    clear grar
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% END OF ODE45 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
end