#!/usr/bin/python3
"""Display petrol fuel prices.  Prices are obtained from a web service.
   Requires requests package ('pip install --upgrade requests')

   The web service returns fuel price info in XML format. 
   Each fuel type is contained in an `<item>` element of the XML, containing: 
    ```
    <item>
      <type>fuel type</type>
      <today>price today</today>
      <tomorrow>price tomorrow</tomorrow>
      <yesterday>price yesterday</yesterday>
      <unit_th>บาท/ลิตร</unit_th>
      <unit_en>Baht/Liter</unit_en>
      <image>url</image>
      <image2>url</image2>
    </item>
    ```
"""
import xml.dom, xml.dom.minidom
import requests


class FuelPrice:
    
    URL = "https://www.bangchak.co.th/api/oilprice"

    # The publication date contained in the data returned by get_fuel_price()
    _publication_date = None

    @classmethod
    def get_fuel_prices(cls):
        """Get petrol fuel prices using data from a web service.
        
        :returns: list of fuel price info. Each element is a
             a dictionary of info for one type of fuel. 
             Keys are the tags in the fuel data obtained from a web service.
        """
        data = cls.get_fuel_data()
        if not data:
            return []
        return cls.parse_data(data)
    
    @classmethod
    def get_fuel_data(cls):
        """Obtain petrol fuel prices from a web service.
        
        :returns: data from the web service in XML format or none if request fails.
        """
        response = requests.get(cls.URL)
        if response.status_code != 200:
            print(f"Response code {response.status_code} from {cls.URL}")
            return None
        return response.content

    @classmethod
    def parse_data(cls, data: str):
        """Parse XML data containing fuel prices, using Bangchak's schema.
    
        :param data:  string containing XML format data for fuel prices.
        :returns: list of dictionaries of fuel data. See get_fuel_prices
            for more detail.
            The fuel 'type' names are reformatted to remove Bangchak's brand
            names from fuels, e.g. remove "S" from "Gasohol 91 S", and
            remove "EVO" from some fuel names. Then convert names to title case.
        """
        document = xml.dom.minidom.parseString(data)
        # get the publication date
        elements = document.getElementsByTagName("update_date")
        # TODO Setting an attribute as a side-effect is bad design.
        cls._publication_date = elements[0].firstChild.nodeValue
        elements = document.getElementsByTagName("item")
        fuels = []
        for element in elements:
            # the child nodes are element.childNodes
            fuel = {}
            for node in element.childNodes:
                tagName = node.tagName
                if tagName in ['image','image2']: continue  # skip image URLs
                fuel[node.tagName] = node.firstChild.nodeValue
            # clean up the fuel name ('type' attribute)
            if 'type' in fuel:
                fueltype = fuel['type'].replace(' S ',' ').replace(' EVO','')
                if fueltype.endswith(' S'):
                    fueltype = fueltype[:-2]
                fuel['type'] = fueltype.title()  # use title case
            fuels.append(fuel)
        return fuels
    
    @classmethod
    def get_date(cls):
        """Get the date of publication of the data. 
        The date value is available only after you call get_price_data.
        :return: (str) the update date-time contained in data returned by the
                 web service. This may not be the same as the last-modified
                 header on the response to the web service request.
        """
        return cls._publication_date
    
    @classmethod
    def get_url(cls):
        """Return the URL of the web service."""
        return cls.URL

