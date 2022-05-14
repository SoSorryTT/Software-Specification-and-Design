from proxypattern import ProxyPattern

def print_fuel_prices(date, fuels):
    """Print the fuel names and prices in a useful format."""
    print(f"{'Fuel Type':20}  Today        Tomorrow")
    for fuel in fuels:
        fueltype = fuel['type']
        change = float(fuel['today']) - float(fuel['yesterday'])
        print(f"{fueltype:20}  {fuel['today']:5} {change:+0.2f}  {fuel['tomorrow']:6} {fuel['unit_en']}")
    print("Updated:", date)

def print_daily_prices(fuels):
    """Print prices for 'yesterday', 'today', and 'tomorrow'
       from the dictionary of fuel price data.
       :param fuels: a List of fuel price data. Each element is
                    a dict of info for a particular fuel type.
    """
    # header line
    print(f"{'Fuel Type':20} {'Yesterday':10} {'Today':10} {'Tomorrow':10}")
    for fuel in fuels:
        fueltype = fuel['type']
        yesterday = fuel['yesterday']
        today = fuel['today']
        tomorrow = fuel['tomorrow']
        print(f"{fueltype:20} {yesterday:10} {today:10} {tomorrow:10}")


if __name__ == '__main__':
    # Get fuel data and publication date from a web service
    proxy_fuel_price = ProxyPattern()
    fuels = proxy_fuel_price.get_fuel_prices()
    date = proxy_fuel_price.get_current_time()

    # print only Gasohol prices (comment out to include Diesel fuels)
    #fuels = [fuel for fuel in fuels if 'Gasohol' in fuel['type']]
    fuels = sorted(fuels, key=lambda fuel: fuel['type'])
    print_fuel_prices(date, fuels)
