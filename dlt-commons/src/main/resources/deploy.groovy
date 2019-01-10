@Library('pipeline-library')
import com.github.okulab.libs.*
node(env.NODE_NAME) {
    def builder = new JenkinsPipelineBootstrap().createBuilder()

    String project = "${PROJECT}"

    String gitBranch = "${GIT_BRANCH}"

    String version = "${PROJECT_VERSION}"

//String destinationPath = "/app/prod/r2g/irn_67491_dll/jobs/dll/v1.2.0"

    String emailAddress = "${NOTIFICATION}"

//String rootPath = "dll-distribution/delivery/dll-distribution-${PROJECT_VERSION}/"

// Oozie Job prefix
//String jobPrefix = "DLL"

// List of Coordinators to be deploy separate with ; char
//String workflowList = "test"

// List of Coordinators to be deploy separate with ; char

//String coordinatorList = "sample"

    boolean skipCoverageAnalisys = true
    boolean skipCodeStyleAnalsys = true
    boolean deployOnNexus = true
    if ("${Skip_Source_Code_Coverage_Analysis}" == "true") skipCoverageAnalisys = true else skipCoverageAnalisys = false
    if ("${Skip_Source_Code_Style_Analysis}" == "true") skipCodeStyleAnalsys = true else skipCodeStyleAnalsys = false
    if ("${Push_Project_Artifact_on_Nexus}" == "true") deployOnNexus = true else deployOnNexus = false

// build project
    builder.mavenApplicationPipeline(skipCoverageAnalisys, skipCodeStyleAnalsys, deployOnNexus, project, version, gitBranch,
            emailAddress, "${IPN}", version)

// push jars on hdfs
//builder.pushApplicationBinariesPipeline(project, destinationPath, version, emailAddress, "${IPN}")

// run oozie jobs
//factory.runApplicationwithOozie(rootPath,jobPrefix,workflowList,coordinatorList,project, destinationPath,  version,  emailAddress)

    println "done"
}