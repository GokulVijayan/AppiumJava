package Reports;

public class TestReportSteps {
	   public String testObjective;
       public String stepName;
       public String stepDescription;
       public String expectedResult;
       public String actualResultPass;
       public String actualResultFail;
       public double responseTime;


       /**
    * Retrieve test objective 
    */
       public String GetTestObjective()
       {
           return testObjective;
       }

       /**
       * Set test objective
       */
       public void SetTestObjective(String testObjective)
       {
           this.testObjective = testObjective;
       }

       /**
       * Retrieve step name
       */
       public String GetStepName()
       {
           return stepName;
       }

       /**
       * Set step name
       */
       public void SetStepName(String stepName)
       {
           this.stepName = stepName;
       }

       /**
       * Retrieve step description
       */
       public String GetStepDescription()
       {
           return stepDescription;
       }

       /**
       * Set step description
       */
       public void SetStepDescription(String stepDescription)
       {
           this.stepDescription = stepDescription;
       }

       /**
       * Retrieve expected result
       */
       public String GetExpectedResult()
       {
           return expectedResult;
       }

       /**
       * Set expected result
       */
       public void SetExpectedResult(String expectedResult)
       {
           this.expectedResult = expectedResult;
       }

       /**
       * Retrieve actual result for test pass 
       */
       public String GetActualResultPass()
       {
           return actualResultPass;
       }

       /**
       * Set actual result for test pass 
       */
       public void SetActualResultPass(String actualResultPass)
       {
           this.actualResultPass = actualResultPass;
       }

       /**
       * Generate actual result for test fail 
       */
       public String GetActualResultFail()
       {
           return actualResultFail;
       }

       /**
       * Set actual result for test fail
       */
       public void SetActualResultFail(String actualResultFail)
       {
           this.actualResultFail = actualResultFail;
       }

       /// <summary>
       /// Get the response time
       /// </summary>
       /// <returns></returns>
       public double GetResponseTime()
       {
           return responseTime;
       }

       /// <summary>
       /// Sets response time
       /// </summary>
       /// <param name="reponseTime"></param>
       public void SetReponseTime(double reponseTime)
       {
           this.responseTime = reponseTime;
       }
   
}
