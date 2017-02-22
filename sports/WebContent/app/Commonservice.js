"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
//@Injectable() specifies class is available to an injector for instantiation and an injector will display an error when trying to instantiate a class that is not marked as @Injectable()
var Commonservice = (function () {
    function Commonservice() {
        this.india_states = [
            "Andaman and Nicobar Islands",
            "Andhra Pradesh",
            "Arunachal Pradesh",
            "Assam",
            "Bihar",
            "Chandigarh",
            "Chhattisgarh",
            "Dadra and Nagar Haveli",
            "Daman and Diu",
            "Delhi",
            "Goa",
            "Gujarat",
            "Haryana",
            "Himachal Pradesh",
            "Jammu and Kashmir",
            "Jharkhand",
            "Karnataka",
            "Kerala",
            "Lakshadweep",
            "Madhya Pradesh",
            "Maharashtra",
            "Manipur",
            "Meghalaya",
            "Mizoram",
            "Nagaland",
            "Orissa",
            "Pondicherry",
            "Punjab",
            "Rajasthan",
            "Sikkim",
            "Tamil Nadu",
            "Tripura",
            "Uttaranchal",
            "Uttar Pradesh",
            "West Bengal"
        ];
        this.deciplines = [
            "VOLLEYBALL",
            "BADMINTON",
            "SHOOTING",
            "BASKET BALL",
            "ATHLETICS",
            "SWIMMING",
            "LANGADI",
            "ARCHERY",
            "WALL CLIMBING",
            "CARROM",
            "KABADDI",
            "Table Tennis",
            "FOOTBALL",
            "KHO KHO",
            "CHESS"
        ];
        this.arrlist = {
            "VOLLEYBALL": {
                B: [{
                        2001: "Under 17 Team",
                        minage: 17
                    }, {
                        2153: "Under 19 Team",
                        minage: 19
                    }]
            },
            "BADMINTON": {
                B: [
                    {
                        2002: "Under 14 SINGLE",
                        minage: 14
                    },
                    {
                        2005: "Under 17 SINGLE",
                        minage: 17
                    }, {
                        2137: "Under 20 SINGLE",
                        minage: 20
                    }
                ],
                "G": [
                    {
                        2003: "Under 14 SINGLE",
                        minage: 14
                    },
                    {
                        2004: "Under 17 SINGLE",
                        minage: 17
                    }, {
                        2138: "Under 20 SINGLE",
                        minage: 20
                    }
                ]
            },
            "SHOOTING": {
                "B": [
                    {
                        2006: "Under 18 OPEN SIGHT",
                        minage: 18
                    },
                    {
                        2008: "Under 18 PIP SIGHT",
                        minage: 18
                    },
                    {
                        2010: "Under 18 AIR PISTOL",
                        minage: 18
                    },
                    {
                        2012: "Under 20 OPEN SIGHT",
                        minage: 20
                    },
                    {
                        2014: "Under 20 PIP SIGHT",
                        minage: 20
                    },
                    {
                        2016: "Under 20 AIR PISTOL",
                        minage: 20
                    }
                ],
                "G": [
                    {
                        2007: "Under 18 OPEN SIGHT",
                        minage: 18
                    },
                    {
                        2009: "Under 18 PIP SIGHT",
                        minage: 18
                    },
                    {
                        2011: "Under 18 AIR PISTOL",
                        minage: 18
                    },
                    {
                        2013: "Under 20 OPEN SIGHT",
                        minage: 20
                    },
                    {
                        2015: "Under 20 PIP SIGHT",
                        minage: 20
                    },
                    {
                        2017: "Under 20 AIR PISTOL",
                        minage: 20
                    }
                ]
            },
            "BASKET BALL": {
                'B': [{
                        2018: "Under 19 Team",
                        minage: 19
                    }]
            },
            "ATHLETICS": {
                "B": [
                    {
                        2019: "Under 12 100 MTR",
                        minage: 12
                    },
                    {
                        2020: "Under 12 200 MTR",
                        minage: 12
                    },
                    {
                        2021: "Under 12 LONG JUMP",
                        minage: 12
                    },
                    {
                        2022: "Under 12 HIGH JUMP",
                        minage: 12
                    },
                    {
                        2023: "Under 12 SHOT PUT",
                        minage: 12
                    },
                    {
                        2029: "Under 14 100 MTR",
                        minage: 14
                    },
                    {
                        2030: "Under 14 200 MTR",
                        minage: 14
                    },
                    {
                        2031: "Under 14 400 MTR",
                        minage: 14
                    },
                    {
                        2032: "Under 14 4 * 100 MTR HURDLE",
                        minage: 14
                    },
                    {
                        2033: "Under 14 LONG JUMP",
                        minage: 14
                    },
                    {
                        2034: "Under 14 HIGH JUMP",
                        minage: 14
                    },
                    {
                        2035: "Under 14 SHOT PUT",
                        minage: 14
                    },
                    {
                        2036: "Under 14 DISCUSS",
                        minage: 14
                    },
                    {
                        2045: "Under 16 100 MTR",
                        minage: 16
                    },
                    {
                        2046: "Under 16 200 MTR",
                        minage: 16
                    },
                    {
                        2047: "Under 16 400 MTR",
                        minage: 16
                    },
                    {
                        2048: "Under 16 110 MTR HURDLE",
                        minage: 16
                    },
                    {
                        2049: "Under 16 LONG JUMP",
                        minage: 16
                    },
                    {
                        2050: "Under 16 HIGH JUMP",
                        minage: 16
                    },
                    {
                        2051: "Under 16 SHOT PUT",
                        minage: 16
                    },
                    {
                        2052: "Under 16 DISCUSS",
                        minage: 16
                    },
                    {
                        2053: "Under 16 800 MTR ",
                        minage: 16
                    },
                    {
                        2054: "Under 16 JAVELLINE",
                        minage: 16
                    },
                    {
                        2055: "Under 16 4*100 RELAY",
                        minage: 16
                    }
                ],
                "G": [
                    {
                        2024: "Under 12 100 MTR",
                        minage: 12
                    },
                    {
                        2025: "Under 12 200 MTR",
                        minage: 12
                    },
                    {
                        2026: "Under 12 LONG JUMP",
                        minage: 12
                    },
                    {
                        2027: "Under 12 HIGH JUMP",
                        minage: 12
                    },
                    {
                        2028: "Under 12 SHOT PUT",
                        minage: 12
                    },
                    {
                        2037: "Under 14 100 MTR",
                        minage: 14
                    },
                    {
                        2038: "Under 14 200 MTR",
                        minage: 14
                    },
                    {
                        2039: "Under 14 400 MTR",
                        minage: 14
                    },
                    {
                        2040: "Under 14 4 * 100 MTR HURDLE",
                        minage: 14
                    },
                    {
                        2041: "Under 14 LONG JUMP",
                        minage: 14
                    },
                    {
                        2042: "Under 14 HIGH JUMP",
                        minage: 14
                    },
                    {
                        2043: "Under 14 SHOT PUT",
                        minage: 14
                    },
                    {
                        2044: "Under 14 DISCUSS",
                        minage: 14
                    },
                    {
                        2056: "Under 16 100 MTR",
                        minage: 16
                    },
                    {
                        2057: "Under 16 200 MTR",
                        minage: 16
                    },
                    {
                        2058: "Under 16 400 MTR",
                        minage: 16
                    },
                    {
                        2059: "Under 16 110 MTR HURDLE",
                        minage: 16
                    },
                    {
                        2060: "Under 16 LONG JUMP",
                        minage: 16
                    },
                    {
                        2061: "Under 16 HIGH JUMP",
                        minage: 16
                    },
                    {
                        2062: "Under 16 SHOT PUT",
                        minage: 16
                    },
                    {
                        2063: "Under 16 DISCUSS",
                        minage: 16
                    },
                    {
                        2064: "Under 16 800 MTR",
                        minage: 16
                    },
                    {
                        2065: "Under 16 JAVELLINE",
                        minage: 16
                    },
                    {
                        2066: "Under 16 4*100 RELAY",
                        minage: 16
                    }
                ]
            },
            "SWIMMING": {
                "B": [
                    {
                        2067: "Under 10 FreeStyle 50 M",
                        minage: 10
                    },
                    {
                        2068: "Under 10 BackStroke 50 M",
                        minage: 10
                    },
                    {
                        2069: "Under 10 BreastStroke 50 M",
                        minage: 10
                    },
                    {
                        2070: "Under 10 Butterfly 50 M",
                        minage: 10
                    },
                    {
                        2075: "Under 12 FreeStyle 50 M",
                        minage: 12
                    },
                    {
                        2076: "Under 12 BackStroke 50 M",
                        minage: 12
                    },
                    {
                        2077: "Under 12 BreastStroke 50 M",
                        minage: 12
                    },
                    {
                        2078: "Under 12 Butterfly 50 M",
                        minage: 12
                    },
                    {
                        2083: "Under 14 FreeStyle 50 M",
                        minage: 14
                    },
                    {
                        2084: "Under 14 BackStroke 50 M",
                        minage: 14
                    },
                    {
                        2085: "Under 14 BreastStroke 50 M",
                        minage: 14
                    },
                    {
                        2086: "Under 14 Butterfly 50 M",
                        minage: 14
                    },
                    {
                        2087: "Under 14 200 MTR IND MIDLAY",
                        minage: 14
                    },
                    {
                        2143: "Under 16 FreeStyle 50 M",
                        minage: 16
                    },
                    {
                        2144: "Under 16 BackStroke 50 M",
                        minage: 16
                    },
                    {
                        2145: "Under 16 BreastStroke 50 M",
                        minage: 16
                    },
                    {
                        2146: "Under 16 Butterfly 50 M",
                        minage: 16
                    },
                    {
                        2147: "Under 16 200 MTR IND MIDLAY",
                        minage: 16
                    }
                ],
                "G": [
                    {
                        2071: "Under 10 FreeStyle 50 M",
                        minage: 10
                    },
                    {
                        2072: "Under 10 BackStroke 50 M",
                        minage: 10
                    },
                    {
                        2073: "Under 10 BreastStroke 50 M",
                        minage: 10
                    },
                    {
                        2074: "Under 10 Butterfly 50 M",
                        minage: 10
                    },
                    {
                        2088: "Under 14 FreeStyle 50 M",
                        minage: 14
                    },
                    {
                        2089: "Under 14 BackStroke 50 M",
                        minage: 14
                    },
                    {
                        2090: "Under 14 BreastStroke 50 M",
                        minage: 14
                    },
                    {
                        2091: "Under 14 Butterfly 50 M",
                        minage: 14
                    },
                    {
                        2092: "Under 14 200 MTR IND MIDLAY",
                        minage: 14
                    },
                    {
                        2079: "Under 12 FreeStyle 50 M",
                        minage: 12
                    },
                    {
                        2080: "Under 12 BackStroke 50 M",
                        minage: 12
                    },
                    {
                        2081: "Under 12 BreastStroke 50 M",
                        minage: 12
                    },
                    {
                        2082: "Under 12 Butterfly 50 M",
                        minage: 12
                    },
                    {
                        2148: "Under 16 FreeStyle 50 M",
                        minage: 16
                    },
                    {
                        2149: "Under 16 BackStroke 50 M",
                        minage: 16
                    },
                    {
                        2150: "Under 16 BreastStroke 50 M",
                        minage: 16
                    },
                    {
                        2151: "Under 16 Butterfly 50 M",
                        minage: 16
                    },
                    {
                        2152: "Under 16 200 MTR IND MIDLAY",
                        minage: 16
                    }
                ]
            },
            "LANGADI": {
                B: [{
                        2093: "Under 14 Team",
                        minage: 14
                    }],
                G: [{
                        2094: "Under 14 Team",
                        minage: 14
                    }]
            },
            "ARCHERY": {
                "B": [
                    {
                        2095: "Under 10 INDIAN ROUND",
                        minage: 10
                    },
                    {
                        2096: "Under 10 RECURVE ROUND",
                        minage: 10
                    },
                    {
                        2099: "Under 14 INDIAN ROUND",
                        minage: 14
                    },
                    {
                        2100: "Under 14 RECURVE ROUND",
                        minage: 14
                    },
                    {
                        2101: "Under 14 COMPOUND ROUND",
                        minage: 14
                    },
                    {
                        2105: "Under 17 INDIAN ROUND",
                        minage: 17
                    },
                    {
                        2106: "Under 17 RECURVE ROUND",
                        minage: 17
                    },
                    {
                        2107: "Under 17 COMPOUND ROUND",
                        minage: 17
                    },
                    {
                        2111: "Under 21 INDIAN ROUND",
                        minage: 21
                    },
                    {
                        2112: "Under 21 RECURVE ROUND",
                        minage: 21
                    },
                    {
                        2113: "Under 21 COMPOUND ROUND",
                        minage: 21
                    }
                ],
                "G": [
                    {
                        2097: "Under 10 INDIAN ROUND",
                        minage: 10
                    },
                    {
                        2098: "Under 10 RECURVE ROUND",
                        minage: 10
                    },
                    {
                        2102: "Under 14 INDIAN ROUND",
                        minage: 14
                    },
                    {
                        2103: "Under 14 RECURVE ROUND",
                        minage: 14
                    },
                    {
                        2104: "Under 14 COMPOUND ROUND",
                        minage: 14
                    },
                    {
                        2108: "Under 17 INDIAN ROUND",
                        minage: 17
                    },
                    {
                        2109: "Under 17 RECURVE ROUND",
                        minage: 17
                    },
                    {
                        2110: "Under 17 COMPOUND ROUND",
                        minage: 17
                    },
                    {
                        2114: "Under 21 INDIAN ROUND",
                        minage: 21
                    },
                    {
                        2115: "Under 21 RECURVE ROUND",
                        minage: 21
                    },
                    {
                        2116: "Under 21 COMPOUND ROUND",
                        minage: 21
                    }
                ]
            },
            "WALL CLIMBING": {
                B: [
                    {
                        2117: "Under 14",
                        minage: 14
                    },
                    {
                        2119: "Under 16",
                        minage: 16
                    }
                ],
                G: [
                    {
                        2118: "Under 14",
                        minage: 14
                    },
                    {
                        2120: "Under 16",
                        minage: 16
                    }
                ]
            },
            "CARROM": {
                B: [
                    {
                        2121: "Under 14",
                        minage: 14
                    },
                    {
                        2123: "Under 16",
                        minage: 16
                    }
                ],
                G: [
                    {
                        2122: "Under 14",
                        minage: 14
                    },
                    {
                        2124: "Under 16",
                        minage: 16
                    }
                ]
            },
            "KABADDI": {
                B: [{
                        2125: "Under 14 Team",
                        minage: 14
                    }, {
                        2156: "Under 18 Team",
                        minage: 18
                    }],
                G: [{
                        2126: "Under 14 Team",
                        minage: 14
                    }, {
                        2157: "Under 18 Team",
                        minage: 18
                    }]
            },
            "Table Tennis": {
                B: [{
                        2127: "Under 14 Team",
                        minage: 14
                    },
                    {
                        2139: "Under 17 Team",
                        minage: 17
                    },
                    {
                        2141: "Under 19 Team",
                        minage: 19
                    }],
                G: [{
                        2128: "Under 14 Team",
                        minage: 14
                    },
                    {
                        2140: "Under 17 Team",
                        minage: 17
                    },
                    {
                        2142: "Under 19 Team",
                        minage: 19
                    }]
            },
            "FOOTBALL": {
                B: [
                    {
                        2129: "Under 14",
                        minage: 14
                    },
                    {
                        2130: "Under 17",
                        minage: 17
                    }
                ]
            },
            "KHO KHO": {
                B: [{
                        2131: "Under 14 Team",
                        minage: 14
                    }, {
                        2154: "Under 18 Team",
                        minage: 18
                    }],
                G: [{
                        2132: "Under 14 Team",
                        minage: 14
                    }, {
                        2155: "Under 18 Team",
                        minage: 18
                    }]
            },
            "CHESS": {
                B: [
                    {
                        2133: "Under 14",
                        minage: 14
                    },
                    {
                        2135: "Under 16",
                        minage: 16
                    }
                ],
                G: [
                    {
                        2134: "Under 14",
                        minage: 14
                    },
                    {
                        2136: "Under 16",
                        minage: 16
                    }
                ]
            }
        };
        this.daylist = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31];
        this.monthlist = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
        this.list = ['ARCHERY', 'ATHLETICS', 'BADMINTON', 'BASKET BALL', 'CARROM', 'CHESS', 'FOOTBALL', 'KABADDI', 'KHO KHO', 'LANGADI', 'SHOOTING', 'SWIMMING', 'Table Tennis', 'VOLLEYBALL', 'WALL CLIMBING'];
    }
    Commonservice = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [])
    ], Commonservice);
    return Commonservice;
}());
exports.Commonservice = Commonservice;
//# sourceMappingURL=Commonservice.js.map