# form-service

### Form
```json 
{
	"id" : "59900d87a260b310433a39b4",
	"title":"survey form has been updated again",
	"elements" : []
}
```
```elements[] ```array will contain individual element as described below

### Short Text
```json
{
	"type" : "short_text",
	"question" : "What is your name",
	"helpText" : "We need this recognise you",
	"required" : true,
	"maxLength" : 6
}
```
### Long Text
```json
{
	"type" : "long_text",
	"question" : "WDescribe yourself",
	"helpText" : "We need this recognise you",
	"required" : true,
	"maxLength" : 4000
}
```
### Number
```json
{
	"type" : "number",
	"question" : "What is your name",
	"helpText" : "We need this recognise you",
	"required" : true
	"minValue" : 5,
	"maxValue" : 999
}
```
### Rates
```json
{
	"type" : "rates",
	"question" : "Rate our service",
	"helpText" : "We need this recognise you",
	"required" : true,
	"rates"    : 5
}
```
### Dropdown
```json
{
	"type" : "dropdown",
	"question" : "School name",
	"helpText" : "We need this recognise you",
	"required" : true,
	"choices" : [
			"dropdown1",
			"dropdown2"
		    ]
}
```
### Mutiple Choice
```json
{
	"type" : "mutiple_choice",
	"question" : "Some Question with mutiple answers",
	"helpText" : "We need this recognise you",
	"required" : true,
	"choices" : [
			"choice1",
			"choice2"
		    ],
	"mutipleAllowed" : false			
}
