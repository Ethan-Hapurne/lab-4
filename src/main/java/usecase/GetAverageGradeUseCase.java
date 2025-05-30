package usecase;

import api.GradeDataBase;
import entity.Grade;
import entity.Team;
import api.GradeDataBase;

/**
 * GetAverageGradeUseCase class.
 */
public final class GetAverageGradeUseCase {
    private final GradeDataBase gradeDataBase;

    public GetAverageGradeUseCase(GradeDataBase gradeDataBase) {
        this.gradeDataBase = gradeDataBase;
    }

    /**
     * Get the average grade for a course across your team.
     * @param course The course.
     * @return The average grade.
     */
    public float getAverageGrade(String course) {
        // Call the API to get usernames of all your team members
        float sum = 0;
        int count = 0;
        // eventually count is just the number of members
        final Team team = gradeDataBase.getMyTeam();
        final String[] teamMembers = team.getMembers();

        for (String member : teamMembers) {
            Grade grade = gradeDataBase.getGrade(member, course);
            sum = sum + grade.getGrade();
            count++;
        }
        // Call the API to get all the grades for the course for all your team members
        if (count == 0) {
            return 0;
        }
        return sum / count;
    }
}
