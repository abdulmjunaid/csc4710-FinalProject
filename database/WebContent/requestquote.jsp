<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Request a Quote</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: flex-start; 
            height: 100vh;
            margin: 0;
            background-color: #ADD8E6;

        }
        .form-container {
            text-align: center;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        
        
      .input-plus {
        display: flex;
      }

      .inputs-set {
        border: none;
      }

      .input-field {
        border: none;
        border: 1px solid rgb(209, 209, 209);
        padding: 8px;
        margin-right: 4px;
        margin-bottom: 4px;
        display: block;
      }
      
    </style>
</head>
<body>
	<form action="viewQuotes" method="post">
			<table cellpadding="5">
				<tr>
					<td colspan="2">
						<input type="submit" value="Back">
					</td>
				</tr>
			</table>
	</form>
	
    <div class="form-container">
        <h1>Request a Quote</h1>
        <button class="btn-add-input" onclick="addTree()" type=""> Add Tree</button>
        <form action="submitRequest" method="post">
        	<input readonly value="Trees: 1" type="text" name="total" id="total" required>
	        <fieldset class="inputs-set" id="trees" class="input-field">
	        	<fieldset class="inputs-set" id="tree" class="input-field">
	        		<h4>Tree 1</h4>
	        		
		            <input type="text" name="firstPic1" id="firstPic1" placeholder = "Image One" required><br>
		            
		            <input type="text" name="secondPic1" id="secondPic1"  placeholder = "Image Two" required><br>

		            <input type="text" name="thirdPic1" id="thirdPic1"  placeholder = "Image Three" required><br>

		            <input type="number" step="0.01" name="size1" id="size1"  placeholder = "Size of Tree in Feet" required><br>

		            <input type="number" step="0.01" name="height1" id="height1"  placeholder = "Height of Tree in Feet" required><br>

		            <input type="number" step="0.01" name="distance1" id="distance1"  placeholder = "Distance from House in Feet" required><br>
		            
	            </fieldset>
	       </fieldset>
	       <textarea name="note" id="note" required placeholder = "Notes" ></textarea><br>
        <input type="submit" value="Submit">
        </form>
    </div>
    

    <script>
      instance = 1;
      const myForm = document.getElementById("trees");
      
      function addTree() {
        // create an input field to insert
        instance += 1;
        const total = document.getElementById("total");
        total.setAttribute("value", "Trees: "+instance);
        
        const tree = document.createElement("H4");
        const treeText = document.createTextNode("Tree " + instance); 
        tree.name = "tree";
        tree.id = "tree";
        tree.appendChild(treeText); 
        
        const firstPicField = document.createElement("input");
        const secondPicField = document.createElement("input");
        const thirdPicField = document.createElement("input");
        const sizeField = document.createElement("input");
        const heightField = document.createElement("input");
        const distanceField = document.createElement("input");
        
        // set input field data type to text
        firstPicField.type = "text";
        secondPicField.type = "text";
        thirdPicField.type = "text";
        sizeField.type = "number";
        heightField.type = "number";
        distanceField.type = "number";
        	
     // set input field name
        firstPicField.name = "firstPic"+ instance;
        secondPicField.name = "secondPic"+ instance;
        thirdPicField.name = "thirdPic"+ instance;
        sizeField.name = "size"+ instance;
        heightField.name = "height"+ instance;
        distanceField.name = "distance"+ instance;
        
        // set input field id
        firstPicField.id = "firstPic"+ instance;
        secondPicField.id = "secondPic"+ instance;
        thirdPicField.id = "thirdPic"+ instance;
        sizeField.id = "size"+ instance;
        heightField.id = "height"+ instance;
        distanceField.id = "distance"+ instance;
        console.log(firstPicField.id, firstPicField.name);
        
        // set required
        firstPicField.setAttribute("required", "");
        secondPicField.setAttribute("required", "");
        thirdPicField.setAttribute("required", "");
        sizeField.setAttribute("required", "");
        heightField.setAttribute("required", "");
        distanceField.setAttribute("required", "");
        
     // set required
        firstPicField.setAttribute("step", "0.01");
        secondPicField.setAttribute("step", "0.01");
        thirdPicField.setAttribute("step", "0.01");
        sizeField.setAttribute("step", "0.01");
        heightField.setAttribute("step", "0.01");
        distanceField.setAttribute("step", "0.01");
        
     	// set placeholder
        firstPicField.setAttribute("placeholder", "Image One");
        secondPicField.setAttribute("placeholder", "Image Two");
        thirdPicField.setAttribute("placeholder", "Image Three");
        sizeField.setAttribute("placeholder", "Size of Tree in Feet");
        heightField.setAttribute("placeholder", "Height of Tree in Feet");
        distanceField.setAttribute("placeholder", "Distance from House in Feet");
        
        
        
        // insert element
        myForm.appendChild(tree);
        myForm.appendChild(firstPicField);
        myForm.appendChild(document.createElement("br"));
        myForm.appendChild(secondPicField);
        myForm.appendChild(document.createElement("br"));
        myForm.appendChild(thirdPicField);
        myForm.appendChild(document.createElement("br"));
        myForm.appendChild(sizeField);
        myForm.appendChild(document.createElement("br"));
        myForm.appendChild(heightField);
        myForm.appendChild(document.createElement("br"));
        myForm.appendChild(distanceField);
        myForm.appendChild(document.createElement("br"));
        
      }
      
    </script>
</body>
</html>
    