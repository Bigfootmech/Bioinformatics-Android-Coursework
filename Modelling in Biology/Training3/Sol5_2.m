    Cleanup

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Plot Phase plane (y vs x) %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    global a b g
    a = 0.8;
    b = 4;
    g = 0.3;
    
    figure(2);
    hold
    xlabel('x')
    ylabel('y')
    
        yn2 = 0:0.001:5;
        for j = 1:length(yn2)
            xn2(j) = 10/(3+3*(yn2(j).^4));
        end
        xn1 = 0:0.001:5;
        for j = 1:length(xn1)
            yn1(j) = 10/(3+3*(xn1(j)^4));   %calculate the nullcline from an analytical solution
        end

        
    plot (xn1, yn1, 'g')           %Plot the y nullcline
    plot (xn2, yn2, 'r')           %Plot the x nullcline
    
    
    
        for i = 1:20
            x0 = 5*rand;
            y0 = 5*rand;
            [t,M]=ode45(@dVdtfun,[0 20],[x0 y0]); %numerical method
            plot (M(:,1),M(:,2), 'b')           %Plot the y/x in time (blue)
        end



    legend ('x nullcline', 'y nullcline', 'random variables')
    