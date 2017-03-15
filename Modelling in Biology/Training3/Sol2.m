function [x_end y_end] = Sol2(X0,Y0)

    close all       %clean up graphs

if nargin < 2
        Y0 = 1;     %simplified use, redundant variables 'default'
        if nargin < 1
            X0 = 2; %simplified use, redundant variables 'default'
        end;
end;



    %%%%%%%%%%%%%%%%%%%%%%%%% Variables %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


    x0 = X0; % BLUE %2
    y0 = Y0; % GREEN %1
    global a b g
    a = 1;
    b = 1;
    g = 0.3;
    
    %%%%%%%%%%%%%%%%%%%%%%% Analytical %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    
    [x_end y_end] = fsolve(@dVdtfun,[x0 y0]);   %analytical fsolve grr. It seems wrong... to me

    
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%% Numerical %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    % V = Vector, M = Matrix
    [t,M]=ode45(@dVdtfun,[0 10],[x0 y0]); %numerical method
    
    subplot(2,1,1)  %divide up a figure, less looking around and stuff
    hold
    xlabel('Time')                      %label
    ylabel('Value (x and y)')           %label
    legend('blue','x', 'green', 'y')    %label

    plot(t,M(:,1),'b')                  %plot x vs time
    %subplot(2,1,2)
    plot(t,M(:,2),'g')                  %plot y vs time (on the same graph)
    hold off
        
    %%%%%%%%%%%%%% Most of this is confused trying to make the dx/x graphs look 'normal'%%%%%%%%%%
    i=2:length(t);              %redundant variable
    io=1:length(t);
    timestep(i)=t(i)-t(i-1);    %time step length
    %timestep(1)=timestep(2);
    dx(i) = (M(i,1)-M((i-1),1));
   % dx(1) = dx(2);
    dxdt(i)=dx(i).*timestep(i);
    dy(i) = (M(i,2)-M((i-1),2));
    %dy(1) = dy(2);
    dydt(i)=dy(i).*timestep(i);
    
    
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Plot dx/x and dy/y %%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    subplot(2,2,3)
    hold
    xlabel('x')
    ylabel('dx')
    plot(M(:,1),dxdt)
    hold off
    
    subplot(2,2,4)
    hold
    xlabel('y')
    ylabel('dy')
    plot(M(:,2),dydt)
    hold off
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Plot Phase plane (y vs x) %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    
    %subplot(3,1,3)
    figure(2);
    hold
    xlabel('x')
    ylabel('y')
    plot (M(:,1),M(:,2), 'b')           %Plot the y/x in time (blue)
    
        if x0 > 5
            xn1 = 0:0.001:x0;       %make sure there are no obvious breaks in the graph
        else
            xn1 = 0:0.001:5;
        end
        for j = 1:length(xn1)
            yn1(j) = b/g(1+xn1(j)^4);   %calculate the nullcline from an analytical solution
        end
        if y0 > 5
            yn2 = 0:0.001:y0;
        else
            yn2 = 0:0.001:5;
        end
        for j = 1:length(yn2)
            xn2(j) = a/g(1+yn2(j).^4);
        end
    
    plot (xn1, yn1, 'g')           %Plot the y nullcline
    plot (xn2, yn2, 'r')           %Plot the x nullcline
    hold off
    
    %S = solve('(a/(1+y^4)-g*x) = 0','(b/(1+x^4)-g*y) = 0', 'a,b,g') % 'a=1','b=1','g=0.3' Nullclines (ie: dx/dt = 0, dy/dt = 0) ---> get out x ,y points to look up in phase plane
    % solve ('243*(y^17)-810*(y^16)+972*(y^13)-3240*(y^12)+1458*(y^9)-4860*(y^8)+972*(y^5)-3240*(y^4)+30243*y-810')

    %LABEL AXES, ONLY POSITIVE
    
end