/**
 *  Copyright 2015 SmartThings
 *  Copyright 2018-2020 Josh McAllister (josh208@gmail.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
metadata {
	// Automatically generated. Make future change here.
	definition (name: "vThermostat Device", namespace: "josh208", author: "Josh McAllister") {
		capability "Thermostat"
		capability "Thermostat Heating Setpoint"
		capability "Thermostat Setpoint"
		capability "Sensor"
		capability "Actuator"

		command "heatUp"
		command "heatDown"
		command "coolUp"
		command "coolDown"
		command "setTemperature", ["number"]
		command "setThermostatThreshold", ["number"]
		command "setMinHeatTemp", ["number"]
		command "setMaxHeatTemp", ["number"]
		command "setMinCoolTemp", ["number"]
		command "setMaxCoolTemp", ["number"]
        command "setMaxUpdateInterval", ["number"]

		attribute "thermostatThreshold", "number"
		attribute "minHeatTemp", "number"
		attribute "maxHeatTemp", "number"
		attribute "minCoolTemp", "number"
		attribute "maxCoolTemp", "number"
        attribute "lastTempUpdate", "date"
        attribute "maxUpdateInterval", "number"
        attribute "preEmergencyMode", "string"
	}

	tiles(scale: 2) {
		multiAttributeTile(name:"thermostatMulti", type:"thermostat", width:6, height:4) {
			tileAttribute("device.temperature", key: "PRIMARY_CONTROL") {
				attributeState("default", label:'${currentValue}', unit:"dF")
			}
			tileAttribute("device.thermostatOperatingState", key: "OPERATING_STATE") {
				attributeState("idle", backgroundColor:"#44b621")
				attributeState("heating", backgroundColor:"#ea5462")
				attributeState("cooling", backgroundColor:"#269bd2")
			}
			tileAttribute("device.thermostatMode", key: "THERMOSTAT_MODE") {
				attributeState("off", label:'${name}')
				attributeState("heat", label:'${name}')
				attributeState("cool", label:'${name}')
				attributeState("auto", label:'${name}')
			}
			tileAttribute("device.heatingSetpoint", key: "HEATING_SETPOINT") {
				attributeState("default", label:'${currentValue}', unit:"dF")
			}
			tileAttribute("device.coolingSetpoint", key: "COOLING_SETPOINT") {
				attributeState("default", label:'${currentValue}', unit:"dF")
			}
		}

		valueTile("temperature", "device.temperature", width: 2, height: 2) {
			state("temperature", label:'${currentValue}', unit:"dF",
				backgroundColors:[
					[value: 31, color: "#153591"],
					[value: 44, color: "#1e9cbb"],
					[value: 59, color: "#90d2a7"],
					[value: 74, color: "#44b621"],
					[value: 84, color: "#f1d801"],
					[value: 95, color: "#d04e00"],
					[value: 96, color: "#bc2323"]
				]
			)
		}
		
		valueTile("heatingSetpoint", "device.heatingSetpoint", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "heat", label:'${currentValue} heat', unit: "F", backgroundColor:"#ffffff"
		}
		standardTile("heatDown", "device.temperature", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "default", label:'down', action:"heatDown"
		}
		standardTile("heatUp", "device.temperature", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "default", label:'up', action:"heatUp"
		}

		valueTile("coolingSetpoint", "device.coolingSetpoint", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "cool", label:'${currentValue} cool', unit:"F", backgroundColor:"#ffffff"
		}
		standardTile("coolDown", "device.temperature", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "default", label:'down', action:"coolDown"
		}
		standardTile("coolUp", "device.temperature", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "default", label:'up', action:"coolUp"
		}
		valueTile("thermostatThreshold", "device.thermostatThreshold", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "default", label:'${currentValue} threshold', unit:"F", backgroundColor:"#ffffff"
		}
		valueTile("maxUpdateInterval", "device.maxUpdateInterval", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "default", label:'${currentValue} minutes', backgroundColor:"#ffffff"
		}      
		valueTile("minHeatTemp", "device.minHeatTemp", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "default", label:'${currentValue} Min', unit:"F", backgroundColor:"#ffffff"
		}
		valueTile("maxHeatTemp", "device.maxHeatTemp", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "default", label:'${currentValue} Max', unit:"F", backgroundColor:"#ffffff"
		}
		valueTile("minCoolTemp", "device.minCoolTemp", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "default", label:'${currentValue} Min', unit:"F", backgroundColor:"#ffffff"
		}
		valueTile("maxCoolTemp", "device.maxCoolTemp", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "default", label:'${currentValue} Max', unit:"F", backgroundColor:"#ffffff"
		}
		standardTile("mode", "device.thermostatMode", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "off", label:'${name}', action:"thermostat.heat", backgroundColor:"#ffffff"
			state "heat", label:'${name}', action:"thermostat.cool", backgroundColor:"#e86d13"
			state "cool", label:'${name}', action:"thermostat.auto", backgroundColor:"#00A0DC"
			state "auto", label:'${name}', action:"thermostat.off", backgroundColor:"#00A0DC"
		}
		standardTile("operatingState", "device.thermostatOperatingState", width: 2, height: 2) {
			state "idle", label:'${name}', backgroundColor:"#ffffff"
			state "heating", label:'${name}', backgroundColor:"#e86d13"
			state "cooling", label:'${name}', backgroundColor:"#00A0DC"
		}

		main("thermostatMulti")
		details([
			"temperature","tempDown","tempUp",
			"mode", "operatingState",
			"heatingSetpoint", "heatDown", "heatUp",
			"coolingSetpoint", "coolDown", "coolUp",
		])
	}
}

def installed() {
	sendEvent(name: "minCoolTemp", value: 60, unit: "F")
	sendEvent(name: "maxCoolTemp", value: 95, unit: "F")
	sendEvent(name: "maxHeatTemp", value: 80, unit: "F")
	sendEvent(name: "minHeatTemp", value: 35, unit: "F")
	sendEvent(name: "thermostatThreshold", value: 1.0, unit: "F")
	sendEvent(name: "temperature", value: 72, unit: "F")
	sendEvent(name: "heatingSetpoint", value: 70, unit: "F")
	sendEvent(name: "thermostatSetpoint", value: 70, unit: "F")
	sendEvent(name: "coolingSetpoint", value: 76, unit: "F")
	sendEvent(name: "thermostatMode", value: "off")
	sendEvent(name: "thermostatOperatingState", value: "idle")
    sendEvent(name: "maxUpdateInterval", value: 65)
    sendEvent(name: "lastTempUpdate", value: new Date() )
}

def updated() {
	sendEvent(name: "minCoolTemp", value: 60, unit: "F")
	sendEvent(name: "maxCoolTemp", value: 95, unit: "F")
	sendEvent(name: "maxHeatTemp", value: 80, unit: "F")
	sendEvent(name: "minHeatTemp", value: 35, unit: "F")
    sendEvent(name: "maxUpdateInterval", value: 65)
    sendEvent(name: "lastTempUpdate", value: new Date() )
}

def parse(String description) {
}

def evaluate(temp, heatingSetpoint, coolingSetpoint) {

	def threshold = device.currentValue("thermostatThreshold")
	def current = device.currentValue("thermostatOperatingState")
	def mode = device.currentValue("thermostatMode")
 
    //Deadman safety. Make sure that we don't keep running if temp is not getting updated.
    def now = new Date().getTime()
    def lastUpdate = Date.parse("E MMM dd H:m:s z yyyy", device.currentValue("lastTempUpdate")).getTime()
    
    def maxInterval = device.currentValue("maxUpdateInterval") ?: 180 //set a somewhat sain limit of 3 hours
    if (maxInterval > 180) maxinterval = 180
    maxInterval = maxInterval * 1000 * 60 //convert maxUpdateInterval (in minutes) to milliseconds
    
    log.debug "now=$now, lastUpdate=$lastUpdate, maxInterval=$maxInterval"
    
    if (now - lastUpdate > maxInterval ) {
        log.info("maxUpdateInterval exceeded. Setting emergencyStop mode")
        sendEvent(name: "preEmergencyMode", value: mode)
        sendEvent(name: "thermostatMode", value: "emergencyStop")
        return
    } else if (mode == "emergencyStop" && device.currentValue("preEmergencyMode")) {
        log.info("Autorecovered from emergencyStop. Resetting to previous mode.")
        sendEvent(name: "thermostatMode", value: device.currentValue("preEmergencyMode"))
        sendEvent(name: "preEmergencyMode", value: "")
        return
    }
    
	if ( !threshold ) {
		log.debug "Threshold was not set. Not doing anything..."
		return
	}
	   
	log.debug "evaluate($temp, $heatingSetpoint, $coolingSetpoint) / threshold=$threshold, mode=$mode,state=$current"
	
	def heating = false
	def cooling = false
	def idle = false

	if (mode in ["heat","emergency heat","auto"]) {
		if (heatingSetpoint - temp >= threshold) {
			heating = true
			sendEvent(name: "thermostatOperatingState", value: "heating")
		}
		else if (temp - heatingSetpoint >= threshold) {
			idle = true
		}
		sendEvent(name: "thermostatSetpoint", value: heatingSetpoint)
	}
	if (mode in ["cool","auto"]) {
		if (temp - coolingSetpoint >= threshold) {
			cooling = true
			sendEvent(name: "thermostatOperatingState", value: "cooling")
		}
		else if (coolingSetpoint - temp >= threshold && !heating) {
			idle = true
		}
		sendEvent(name: "thermostatSetpoint", value: coolingSetpoint)
	}
	else {
		sendEvent(name: "thermostatSetpoint", value: heatingSetpoint)
	}
	if (idle && !heating && !cooling) {
		sendEvent(name: "thermostatOperatingState", value: "idle")
	}
}

def setHeatingSetpoint(degreesF){
	setHeatingSetpoint(degreesF.toDouble())
}

def setHeatingSetpoint(Double degreesF) {
	def min = device.currentValue("minHeatTemp")
	def max = device.currentValue("maxHeatTemp")
	if (degreesF > max || degreesF < min) {
		log.debug "setHeatingSetpoint is ignoring out of range request ($degreesF)."
		return
	}
	log.debug "In setHeatingSetpoint"
	log.debug "setHeatingSetpoint($degreesF)"
	sendEvent(name: "heatingSetpoint", value: degreesF)
	evaluate(device.currentValue("temperature"), degreesF, device.currentValue("coolingSetpoint"))
}

def setCoolingSetpoint(degreesF){
	setHeatingSetpoint(defreesF.toDouble())
}

def setCoolingSetpoint(Double degreesF) {
	def min = device.currentValue("minCoolTemp")
	def max = device.currentValue("maxCoolTemp")
	if (degreesF > max || degreesF < min) {
		log.debug "setCoolingSetpoint is ignoring out of range request ($degreesF)."
		return
	}
	log.debug "setCoolingSetpoint($degreesF)"
	sendEvent(name: "coolingSetpoint", value: degreesF)
	evaluate(device.currentValue("temperature"), device.currentValue("heatingSetpoint"), degreesF)
}

def setThermostatThreshold(Double degreesF) {
	log.debug "setThermostatThreshold($degreesF)"
	sendEvent(name: "thermostatThreshold", value: degreesF)
	evaluate(device.currentValue("temperature"), device.currentValue("heatingSetpoint"), device.currentValue("coolingSetpoint"))
}

def setMaxUpdateInterval(BigDecimal minutes) {
    sendEvent(name: "maxUpdateInterval", value: minutes)
    evaluate(device.currentValue("temperature"), device.currentValue("heatingSetpoint"), device.currentValue("coolingSetpoint"))
}

def setThermostatMode(String value) {
	sendEvent(name: "thermostatMode", value: value)
	evaluate(device.currentValue("temperature"), device.currentValue("heatingSetpoint"), device.currentValue("coolingSetpoint"))
}

def off() {
	sendEvent(name: "thermostatMode", value: "off")
	evaluate(device.currentValue("temperature"), device.currentValue("heatingSetpoint"), device.currentValue("coolingSetpoint"))
}

def emergencyStop() {
    sendEvent(name: "thermostatMode", value: "emergency stop")
    evaluate(device.currentValue("temperature"), device.currentValue("heatingSetpoint"), device.currentValue("coolingSetpoint"))
}

def heat() {
	sendEvent(name: "thermostatMode", value: "heat")
	evaluate(device.currentValue("temperature"), device.currentValue("heatingSetpoint"), device.currentValue("coolingSetpoint"))
}

def auto() {
	sendEvent(name: "thermostatMode", value: "auto")
	evaluate(device.currentValue("temperature"), device.currentValue("heatingSetpoint"), device.currentValue("coolingSetpoint"))
}

def emergencyHeat() {
	sendEvent(name: "thermostatMode", value: "emergency heat")
	evaluate(device.currentValue("temperature"), device.currentValue("heatingSetpoint"), device.currentValue("coolingSetpoint"))
}

def cool() {
	sendEvent(name: "thermostatMode", value: "cool")
	evaluate(device.currentValue("temperature"), device.currentValue("heatingSetpoint"), device.currentValue("coolingSetpoint"))
}

def poll() {
	null
}


def setTemperature(value) {
	sendEvent(name:"temperature", value: value)
    sendEvent(name: "lastTempUpdate", value: new Date() )
	evaluate(value, device.currentValue("heatingSetpoint"), device.currentValue("coolingSetpoint"))
}

def heatUp() {
	def ts = device.currentValue("heatingSetpoint")
	setHeatingSetpoint( ts + 1 )
}

def heatDown() {
	def ts = device.currentValue("heatingSetpoint")
	setHeatingSetpoint( ts - 1 )
}


def coolUp() {
	def ts = device.currentValue("heatingSetpoint")
	setCoolingSetpoint( ts + 1 )
}

def coolDown() {
	def ts = device.currentValue("heatingSetpoint")
	setCoolingSetpoint( ts - 1 )
}

def setMinCoolTemp(Double degreesF) {
	def t = device.currentValue("coolingSetpoint")
	sendEvent(name: "minCoolTemp", value: degreesF)
	if (t < degreesF) {
		setCoolingSetpoint(degreesF)
	}
}

def setMaxCoolTemp(Double degreesF) {
	def t = device.currentValue("coolingSetpoint")
	sendEvent(name: "maxCoolTemp", value: degreesF)
	if (t > degreesF) {
		setCoolingSetpoint(degreesF)
	}
}

def setMinHeatTemp(Double degreesF) {
	def t = device.currentValue("heatingSetpoint")
	sendEvent(name: "minHeatTemp", value: degreesF)
	if (t < degreesF) {
		setHeatingSetpoint(degreesF)
	}
}

def setMaxHeatTemp(Double degreesF) {
	def t = device.currentValue("heatingSetpoint")
	sendEvent(name: "maxHeatTemp", value: degreesF)
	if (t > degreesF) {
		setHeatingSetpoint(degreesF)
	}
}
