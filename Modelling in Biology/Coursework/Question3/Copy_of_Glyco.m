    Cleanup
    
    global k1 k2 vin
    k1 = 1;
    vin = 0.5;
    x0 = 0;
    y0 = 0;
    yn1 = 0:0.001:10;
    count = 1;
    
    for k2 = 0.128:0.001:0.13
    	[t,X]=ode45(@Glycofun,[0 5000],[x0 y0]); %numerical method
    	figure(count)
        hold all
        plot (t,X(:,1), 'g')           %Plot the y/x in time (blue)
        plot (t,X(:,2), 'r')           %Plot the y/x in time (blue)
        legend ('x', 'y')
        xlabel('t')
        ylabel('Amplitude')
        str = ['Time plane, k2 = ' num2str(k2)];
        title(str)
        figure(count+1)
        hold all
        xn1 = vin./(k2 + k1*yn1.^2);
        xn2 = yn1./(k2 + k1*yn1.^2);
        plot (xn1, yn1, 'g')
        plot (xn2, yn1, 'r')
        plot (X(:,1),X(:,2), 'b')           %Plot the y/x in time (blue)
        plot(X(1,1),X(1,2),'x','Color', [0 0 0])   %plot starting point
        xlabel('x')
        ylabel('y')
        legend ('dx/dt = 0', 'dy/dt = 0', 'phase plane trajectory', 'initial conditions')
        str = ['Phase plane, k2 = ' num2str(k2)];
        title(str)
        count = count + 2;
	end
    