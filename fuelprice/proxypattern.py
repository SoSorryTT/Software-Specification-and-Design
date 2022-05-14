from re import S
from fuelprice import *
import os.path, datetime, json

class ProxyPattern:
    _FILENAME = 'proxy.json'
    _today_time = datetime.datetime.today().strftime("%Y-%m-%d")
    _data = {}

    def __init__(self):
        if (os.path.exists(self._FILENAME)):
            self._data = json.load(open(self._FILENAME))
            if (self._data['today'] != self._today_time):
                self.update()
        else:
            self.update()

    def update(self):
        """Update the data to be current used."""
        self._data['fuels'] = FuelPrice.get_fuel_prices()
        self._data['date'] = FuelPrice.get_date()
        self._data['today'] = self._today_time
        json.dump(self._data, open(self._FILENAME, 'w'))

    def get_fuel_price(self):
        """Return fuel price."""
        return self._data['fuels']

    def get_current_time(self):
        """Return current date time."""
        return self._data['date']            
