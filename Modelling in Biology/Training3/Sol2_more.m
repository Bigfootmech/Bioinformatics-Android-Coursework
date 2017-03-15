    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Plot Phase plane (y vs x) %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    global a b g
    a = 1;
    b = 1;
    g = 0.3;
    
    figure(2);
    hold
    xlabel('x')
    ylabel('y')
    
        for x0 = 0:0.5:5
            for y0 = 0:0.5:5
                [t,M]=ode45(@dVdtfun,[0 10],[x0 y0]); %numerical method
                plot (M(:,1),M(:,2), 'b')           %Plot the y/x in time (blue)
            end
        end
    
    
    
        if x0 > 5
            xn1 = 0:0.001:x0;       %make sure there are no obvious breaks in the graph
        else
            xn1 = 0:0.001:5;
        end
        for j = 1:length(xn1)
            yn1(j) = 10/(3+3*(xn1(j)^4));   %calculate the nullcline from an analytical solution
        end
        if y0 > 5
            yn2 = 0:0.001:y0;
        else
            yn2 = 0:0.001:5;
        end
        for j = 1:length(yn2)
            xn2(j) = 10/(3+3*(yn2(j).^4));
        end
    plot (xn1, yn1, 'g')           %Plot the y nullcline
    plot (xn2, yn2, 'r')           %Plot the x nullcline
    