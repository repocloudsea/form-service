# form-service

```json
{
	"type" : "short_text",
	"question" : "What is your name",
	"helpText" : "We need this recognise you",
	"required" : true,
	"maxLength" : 6
}
```
```json
{
	"type" : "long_text",
	"question" : "WDescribe yourself",
	"helpText" : "We need this recognise you",
	"required" : true,
	"maxLength" : 4000
}
```
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
```json
{
	"type" : "rates",
	"question" : "Rate our service",
	"helpText" : "We need this recognise you",
	"required" : true,
	"rates"    : 5
}
```

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
```

