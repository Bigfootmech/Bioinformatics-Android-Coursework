Cleanup

t = 1:1:30;
for r = [0.5 1.5 2.5 3.2 3.5 4]
    figure
    E = myfun(r, t);
    plot(t-1,E)%, 'x')
    legend ('Points of E')
    xlabel('t')
    ylabel('E')
    title('Displacement vs time')
end