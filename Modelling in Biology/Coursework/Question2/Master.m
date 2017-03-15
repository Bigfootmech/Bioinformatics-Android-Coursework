Cleanup

t = 1:1:30;
for a = [-2 1 -1 -0.5 0.5 2]
    figure
    E = DynSys(a, t);
    plot(t-1,E, 'x')
    legend ('Points of E')
    xlabel('t')
    ylabel('E')
    title('Displacement vs time')
end