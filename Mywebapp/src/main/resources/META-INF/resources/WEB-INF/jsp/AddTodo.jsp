<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<h1>Enter Todo Details</h1>
	<div>please enter more than 5 character description</div>
	<form:form method="Post" modelAttribute="AddTodo">
		<fieldset class="mb-3">
			<form:label path="description">Description</form:label>
			<form:input type="text" path="description" required="required" />
			<form:errors path="description" />
		</fieldset>
		<fieldset class="mb-3">
			<form:label path="targetDate">TargetDate</form:label>
			<form:input type="text" path="targetDate" required="required" />
			<form:errors path="targetDate" />
		</fieldset>

		<form:input type="hidden" path="id" />
		<form:input type="hidden" path="completionStatus" />
		<input type="submit" class="btn btn-success" />

	</form:form>

</div>
<%@ include file="common/footer.jspf"%>

<script type="text/javascript">
	$('#targetDate').datepicker({
		format : 'yyyy-mm-dd',
	});
</script>

